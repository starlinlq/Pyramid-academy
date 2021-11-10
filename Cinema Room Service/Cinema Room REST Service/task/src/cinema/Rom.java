package cinema;

import java.util.ArrayList;
import java.util.List;

public class Rom {
    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats = new ArrayList<>();

    public Rom(){}
    public Rom(int total_rows, int total_columns){
        this.total_rows= total_rows;
        this.total_columns = total_columns;
        this.genSeats();
    }

    private void genSeats(){

        for(int i = 1 ; i <= total_rows; i++){
            for(int j = 1; j <= total_columns; j++){
                if(i <= 4){
                    available_seats.add(new Seat(i, j, false, 10));
                } else
                    available_seats.add(new Seat(i, j , false, 8));

            }
        }
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }
}
