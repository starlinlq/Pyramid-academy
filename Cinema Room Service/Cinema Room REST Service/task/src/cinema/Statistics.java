package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {
    @JsonProperty("current_income")
    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private int numberOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int numberOfPurchaseTickets;

    Statistics(){}
    Statistics(int numberOfAvailableSeats){
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }
    Statistics(int currentIncome, int numberOfAvailableSeats, int numberOfPurchaseTickets){
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchaseTickets = numberOfPurchaseTickets;
    }

    public int getNumberOfPurchaseTickets() {
        return numberOfPurchaseTickets;
    }

    public void setNumberOfPurchaseTickets(int numberOfPurchaseTickets) {
        this.numberOfPurchaseTickets += numberOfPurchaseTickets;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome += currentIncome;
    }
}
