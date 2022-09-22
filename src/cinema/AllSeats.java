package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@Service
public class AllSeats {
    private ArrayList<SeatWithTicket> available_seats = new ArrayList<>();
    private final int total_rows = 9;
    private final int total_columns = 9;
    AllSeats(){
            /*this.available_seats = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    available_seats.add(new Seat(i, j));
                }
            }*/
    }

    public void setAvailable_seats() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                available_seats.add(new SeatWithTicket(new Seat(i, j)));
            }
        }
    }

    public ArrayList<Seat> getAvailable_seats() {
        ArrayList<Seat> available_seats1 = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            if (available_seats.get(i).isAvailable()) {
                available_seats1.add(available_seats.get(i).getSeat());
            }
        }
        return available_seats1;
    }
    public ArrayList<SeatWithTicket> getAll_seats(){
        return available_seats;
    }
    public void deleteSeat(int i){
        this.available_seats.get(i).setAvailable(false);
    }
    public Map<String,Integer> getStats(){
        int income = 0, purchased = 0,available = 0;
        for(SeatWithTicket seat: getAll_seats()){
            if(seat.isAvailable()){
                available++;
            }else{
                purchased++;
                income += seat.getSeat().getPrice();
            }
        }
        return Map.of("current_income",income,
                "number_of_available_seats", available,
                "number_of_purchased_tickets", purchased);
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }
}

