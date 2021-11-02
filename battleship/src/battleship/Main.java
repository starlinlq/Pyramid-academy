package battleship;

import java.util.Scanner;

enum Ships {
    AIRCRAFTCARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private final String name;
    private final int cells;

     Ships(String name, int cells){
        this.name = name;
        this.cells = cells;
    }

    String getName(){
       return this.name;
    }

    int getCells(){
         return this.cells;
    }
}

public class Main {
    static Table player1 = new Table();
    static Table player2 = new Table();
    static Scanner scanner = new Scanner(System.in);
    static int shipN = 0;
    public static void main(String[] args) {
        // Write your code here
        //change to classes that extends from abstract class Ship;

        Ship aircraftCarrier = new AircraftCarrier("AircraftCarrier", 5);
        Ship battleship = new Battleship("Battleship",4);
        Ship Submarine = new Submarine("Submarine", 3);
        Ship cruiser = new Cruiser("Cruiser", 3);
        Ship destroyer = new Destroyer("Destroyer", 2);


        System.out.print("\n");
        System.out.println("Player 1, place your ships on the game field");
        player1.displayTable();
        for(Ships ship: Ships.values()){
            setPositions("Enter the coordinates of the "+ ship.getName()+" (" + ship.getCells()+" cells)", ship.getCells(), player1);
        }
        shipN = 0;
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        System.out.println("Player 2, place your ships on the game field");
        player2.displayTable();
        for(Ships ship: Ships.values()){
            setPositions("Enter the coordinates of the "+ ship.getName()+" (" + ship.getCells()+" cells)", ship.getCells(), player2);
        }
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        beginGame();
    }

    static void setPositions(String text, int length, Table player){
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        while(true){
            String p1 = scanner.next();
            String p2 = scanner.next();
            if(!player.setPosition(p1, p2, length, shipN)){
                System.out.print(" Try again: ");
                continue;
            }
            player.displayTable();
            break;
        }
        shipN += 1;
    }

    static void beginGame(){
        System.out.print("\n");

        while(!player1.isOver && !player2.isOver){
            displayPLayerTables(player1);
            System.out.println("Player 1, it's your turn:");
            makePLay(player2);
            pressEnter();

            displayPLayerTables(player2);

            System.out.println("Player 2, it's your turn:");
            makePLay(player1);
            pressEnter();
        }
    }

    static void pressEnter(){
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }
    static void displayPLayerTables(Table player){
        player.displayFogOfWar();
        System.out.println("---------------------");
        player.displayTable();
    }

    static void makePLay(Table player){
        while(true){
            String shot = scanner.nextLine();
            boolean valid = player.setShot(shot);
            if(valid){
                break;
            }
        }
    }

}
