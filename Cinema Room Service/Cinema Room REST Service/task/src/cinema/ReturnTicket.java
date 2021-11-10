package cinema;

public class ReturnTicket {
    Seat returned_ticket;

    ReturnTicket(Seat returned_ticket){
        this.returned_ticket = returned_ticket;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
