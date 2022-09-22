package cinema;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class SeatsController {
   /* private List<Seat> available_seats;
    private final int total_rows = 9;
    private final int total_columns = 9;

    public final List<Seat> getAvailable_seats(int rows, int columns) {
        available_seats = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                available_seats.add(new Seat(i, j));
            }
        }
        return available_seats;
    }*/
    static AllSeats availableSeats;
    static {
        availableSeats = new AllSeats();
        availableSeats.setAvailable_seats();
    }
    @GetMapping(value = "/seats",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cinema get(){
        return new Cinema(availableSeats.getTotal_rows(), availableSeats.getTotal_columns(), availableSeats.getAvailable_seats());
    }
    @GetMapping("/available_seats/{id}")
    public ResponseEntity<?> getByValue(@PathVariable int id){
        int i = 0;
        if(id>81||id<1){
            return new ResponseEntity<>(Map.of("error","index out of bounds!"),HttpStatus.BAD_REQUEST);
        }
        for(SeatWithTicket seats: availableSeats.getAll_seats()){
            if(seats.isAvailable()){
                if(id==i){
                    return new ResponseEntity(seats.getSeat(),HttpStatus.OK);
                }
                i++;
            }
        }
        return new ResponseEntity(Map.of("error","index not found"),HttpStatus.BAD_REQUEST);
    }


    @PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> purchase(@RequestBody Seat seatsToPurchase){
        if(seatsToPurchase.getColumn()>9||seatsToPurchase.getColumn()<1||seatsToPurchase.getRow()>9||seatsToPurchase.getRow()<1){
            return new ResponseEntity<>(new AppError("The number of a row or a column is out of bounds!"),HttpStatus.BAD_REQUEST);
        }
        int i = 0;
        for(SeatWithTicket seats : availableSeats.getAll_seats()){
            if(seats.getSeat().compareTo(seatsToPurchase)==0&&seats.isAvailable()){
                availableSeats.deleteSeat(i);
                return new ResponseEntity<>(Map.of("token", seats.getToken(),"ticket",seats.getSeat()),HttpStatus.OK);
            }
            i++;
        }
        return new ResponseEntity<>(new AppError("The ticket has been already purchased!"),HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/return")
    public ResponseEntity<?> returnPost(@RequestBody Token token){
        for(SeatWithTicket ticket : availableSeats.getAll_seats()){
            if(ticket.getToken().equals(token.getToken())&& !ticket.isAvailable()) {
                ticket.setAvailable(true);
                return new ResponseEntity<>(Map.of("returned_ticket", ticket.getSeat()), HttpStatus.OK);
            }
        }
        return new ResponseEntity(Map.of("error","Wrong token!") ,HttpStatus.BAD_REQUEST);
    }
    @PostMapping(value = "/stats")
    public ResponseEntity<?> statsPost(@RequestParam(required = false) String password){
        if(password==null){
            return new ResponseEntity<>(Map.of("error","The password is wrong!"),HttpStatus.valueOf(401));
        }else
        if(password.equals("super_secret")){
            return new ResponseEntity(availableSeats.getStats(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Map.of("error","The password is wrong!"),HttpStatus.valueOf(401));
        }
    }
}

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException(String cause){
        super(cause);
    }

}