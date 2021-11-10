package cinema;

import java.util.UUID;

public class Purchased {
    UUID token;
    Seat ticket;

    Purchased(UUID token, Seat seat){
        this.token = token;
        this.ticket = seat;
    }

    public Seat getTicket(){
        return this.ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token){
        this.token = token;
    }

    public void setTicket(Seat seat){
        this.ticket = ticket;
    }
}
