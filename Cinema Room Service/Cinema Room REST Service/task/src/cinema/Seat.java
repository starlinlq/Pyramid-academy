package cinema;


public class Seat {
    private int row;
    private int column;
    private int price;

    Seat(){};
    Seat(int row,  int column, boolean isPurchased, int price){
        this.row = row;
        this.column = column;
        this.price = price;

    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column){
        this.column = column;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
