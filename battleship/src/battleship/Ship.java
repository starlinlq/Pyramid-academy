package battleship;

abstract class Ship {
    private String name;
    private int cells;

     Ship(String name, int cells){
        this.name = name;
        this.cells = cells;
    };

     String getName(){
         return name;
     }

     int getCells(){
         return cells;
     }

     void setName(String n){
        name = n;
     }

     void setCells(int c){
        cells = c;
     }

}
