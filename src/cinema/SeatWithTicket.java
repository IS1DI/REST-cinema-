package cinema;

import java.util.UUID;

public class SeatWithTicket {
    SeatWithTicket(){

    }
    SeatWithTicket(Seat seat){
        this.seat = seat;
        token = UUID.randomUUID();
        available = true;
    }
    private boolean available;
    private UUID token;
    private Seat seat;

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
    public Seat getSeat() {
        return seat;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available){
        this.available = available;
    }
}
