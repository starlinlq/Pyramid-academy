package battleship;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    private String[][] table = new String[10][10];
    private final List<Character> arr = new ArrayList<>(List.of('A','B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'));
    private String[][] shipPositions  = new String[5][4];
    private String[][] warTable = new String[10][10];
    int numberOfSinks = 0;
    boolean isOver = false;

    Table(){
        generateTable(table);
        generateTable(warTable);
    }

    public void generateTable(String[][] t){
        for(int i = 0; i < t.length; i++){
            for(int j = 0; j < t.length; j++){
                t[i][j] = "~ ";
            }
        }
    };

    public void displayFogOfWar(){
        int chNum = 65;
        char ch;
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (String[] column : warTable) {
            ch = (char) chNum;
            for (int row = 0; row < warTable.length; row++) {
                if(row == 0){
                    System.out.print(ch+" "+"~ ");
                }else if (row == 9) {
                    System.out.print("~ " + "\n");
                } else
                    System.out.print("~ ");
            }
            chNum++;
        }
    }

    public void displayTable(){
        int chNum = 65;
        char ch;
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (String[] column : table) {
            ch = (char) chNum;
            for (int row = 0; row < table.length; row++) {
                if(row == 0){
                    System.out.print(ch+" "+column[row]);
                }else if (row == 9) {
                    System.out.print(column[row] + "\n");
                } else
                    System.out.print(column[row]);
            }
            chNum++;
        }
    }

    public boolean verifyCoordinatesHorizontal(String[] x1, String[] x2, String sing, boolean active){

        if(Integer.parseInt(x1[0]) > 0 && Integer.parseInt(x1[0]) < 9){
            if(sing.equals("+")){
                x1[0] = String.valueOf(Integer.parseInt(x1[0]) + 1);
                x2[0] = String.valueOf(Integer.parseInt(x2[0]) + 1);
            } else if(sing.equals("-")) {
                x1[0] = String.valueOf(Integer.parseInt(x1[0]) - 1);
                x2[0] = String.valueOf(Integer.parseInt(x2[0]) - 1);
            }
        } else {
           if(active && Integer.parseInt(x1[0]) < 9){
               x1[0] = String.valueOf(Integer.parseInt(x1[0]) + 1);
               x2[0] = String.valueOf(Integer.parseInt(x2[0]) + 1);
           }
        }

        if(!active){
            if(Integer.parseInt(x1[1]) > 1 && Integer.parseInt(x2[1]) < 10 ){
                x1[1] = String.valueOf((Integer.parseInt(x1[1]) - 1));
                x2[1] = String.valueOf((Integer.parseInt(x2[1]) + 1));
            } else if (Integer.parseInt(x2[1]) < 10){
                x2[1] = String.valueOf((Integer.parseInt(x2[1]) + 1));
            }
        }


        for(int i = Integer.parseInt(x1[0]); i <=  Integer.parseInt(x2[0]); i++){
            // if x1[1] < x2[1]
            if(Integer.parseInt(x1[1]) < Integer.parseInt(x2[1])){
                for(int j = Integer.parseInt(x1[1]) - 1; j <= Integer.parseInt(x2[1]) -1 ; j++){
                    if(!table[i][j].equals("~ ")){
                        return false;
                    }
                }

            } else
                //if x1[1] > x2[1]
                for(int j = Integer.parseInt(x1[1]) - 1; j >= Integer.parseInt(x2[1]) - 1; j--){
                    if(!table[i][j].equals("~ ")){
                        return false;
                    }
                }
        };
        return true;
    }

    public int parse(String n){
        return Integer.parseInt(n);
    }

    public boolean setShot(String input){
        String[] coordinates = input.split("");

        String value = "0";

        if(coordinates.length == 3){
            value = input.split("")[1] + input.split("")[2];
            coordinates[1] = "10";
        }

        if(!arr.contains(coordinates[0].toCharArray()[0]) || parse(value) > 10 ){
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }

        coordinates[0] = String.valueOf(arr.indexOf(coordinates[0].toCharArray()[0]));
        coordinates[1] = String.valueOf((parse(coordinates[1]) - 1));



        if(table[parse(coordinates[0])][parse(coordinates[1])].equals("O ") || table[parse(coordinates[0])][parse(coordinates[1])].equals("X ")){
            table[parse(coordinates[0])][parse(coordinates[1])] = "X ";
            warTable[parse(coordinates[0])][parse(coordinates[1])] = "X ";
            boolean verify = verifysink();
            if(!verify && numberOfSinks != 5 && !isOver){
                System.out.println("You hit a ship!");
                return true;
            }

        } else {
            table[parse(coordinates[0])][parse(coordinates[1])] = "M ";
            warTable[parse(coordinates[0])][parse(coordinates[1])] = "M ";
            System.out.println("You missed!");
            return true;
        }

        return true;
    }

    public boolean verifysink() {
        int x = 0;

        while(x < 5){

            int count = 0;
            String[] coordinates = shipPositions[x];
            String[] x1 = coordinates[0].split("");
            String[] x2 =  coordinates[1].split("");
            String length = coordinates[2];
            String isSank = coordinates[3];

            if(x2.length > 2){
                x2[1] = "10";

            }

            if(x1.length > 2){
                x1[1] = "10";
            }

            for(int i = Integer.parseInt(x1[0]); i <=  Integer.parseInt(x2[0]); i++){
                // if x1[1] < x2[1]
                if(Integer.parseInt(x1[1]) <= Integer.parseInt(x2[1])){
                    for(int j = Integer.parseInt(x1[1]) - 1; j <= Integer.parseInt(x2[1]) - 1 ; j++){
                       if(table[i][j].equals("X ")){
                           count++;
                       }
                    }
                } else {
                    //if x1[1] > x2[1]
                    for(int j = Integer.parseInt(x1[1]) - 1; j >= Integer.parseInt(x2[1]) - 1; j--){
                        if(table[i][j].equals("X ")){
                            count++;
                        }
                    }
                }
            };

            if(count == parse(length) && parse(isSank) != 1){
                coordinates[3] = "1";
                numberOfSinks += 1;

                if(numberOfSinks == 5){
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    isOver = true;
                    return true;
                }
                System.out.println("You sank a ship! Specify a new target:");
                return true;
            }

            x++;

        }
        return false;
    }

    public boolean verifyCoordinatesVertical(String[] x1, String[] x2, String sing, boolean active){

        if(Integer.parseInt(x1[1]) > 1){
            if(sing.equals("-")){
                x1[1] = String.valueOf(Integer.parseInt(x1[1]) - 1);
                x2[1] = String.valueOf(Integer.parseInt(x2[1]) - 1);
            } else if(sing.equals("+")){
                x1[1] = String.valueOf(Integer.parseInt(x1[1]) + 1);
                x2[1] = String.valueOf(Integer.parseInt(x2[1]) + 1);
            }

        } else {
           if(active){
               x1[1] = String.valueOf(Integer.parseInt(x1[1]) + 1);
               x2[1] = String.valueOf(Integer.parseInt(x2[1]) + 1);
           }
        }

        if(Integer.parseInt(x1[0]) > 0 && Integer.parseInt(x2[0]) < 9){
            x1[0] = String.valueOf((Integer.parseInt(x1[0]) - 1));
            x2[0] = String.valueOf((Integer.parseInt(x2[0]) + 1));
        } else if (Integer.parseInt(x2[0]) < 9){
            x2[0] = String.valueOf((Integer.parseInt(x2[0]) + 1));
        }

        for(int i = Integer.parseInt(x1[0]); i <=  Integer.parseInt(x2[0]); i++){
            // if x1[0] < x2[0]
            for(int j = Integer.parseInt(x1[1]) - 1; j <= Integer.parseInt(x2[1]) - 1 ; j++){
                if(!table[i][j].equals("~ ")){
                    return false;
                }
            }
        }

        return true;
    }


    public boolean isEmpty(String[] x1, String[] x2){
        for(int i = Integer.parseInt(x1[0]); i <=  Integer.parseInt(x2[0]); i++){
            // if x1[1] < x2[1]
            if(Integer.parseInt(x1[1]) <= Integer.parseInt(x2[1])){
                for(int j = Integer.parseInt(x1[1]) - 1; j <= Integer.parseInt(x2[1]) - 1 ; j++){
                     if(!table[i][j].equals("~ ")){
                         return false;
                     }
                }
            } else {
                //if x1[1] > x2[1]
                for(int j = Integer.parseInt(x1[1]) - 1; j >= Integer.parseInt(x2[1]) - 1; j--){
                   if(!table[i][j].equals("~ ")){
                       return false;
                   }
                }
            }
        };
        return true;
    }

    public boolean setPosition(String p1, String p2, int length, int shipN){

        String[] x1 = p1.split("");
        x1[0] = String.valueOf(arr.indexOf(x1[0].toCharArray()[0]));
        String[] x2 = p2.split("");
        x2[0] = String.valueOf(arr.indexOf(x2[0].toCharArray()[0]));


        if(Integer.parseInt(x1[0]) > Integer.parseInt(x2[0])){
            String[] holder = x2;
            x2 = x1;
            x1 = holder;
        }

        if(Integer.parseInt(x1[0]) < Integer.parseInt(x2[0])){
            if(Integer.parseInt(x1[1]) != Integer.parseInt(x2[1])){
                System.out.print("Error! Wrong ship location! Try again:");
                return false;
            } else if ((Integer.parseInt(x2[0]) - Integer.parseInt(x1[0])) != length - 1 ){
                System.out.println("Error! Wrong length! Try again:");
                return false;
            }
        }


        if(x1.length == 3){
            x1[1] = "10";
        } else if (x2.length == 3){
            x2[1] = "10";
        }

        if((Integer.parseInt(x2[1]) - Integer.parseInt(x1[1])) >= length){
            System.out.print("Error! Wrong length! Try again:");
            return false;
        }

        boolean valid1;
        boolean valid2;
        boolean valid3;

        if(x1[0].equals(x2[0])){
           valid1 = verifyCoordinatesHorizontal(x1.clone(), x2.clone(),"+", true);
           valid2 =verifyCoordinatesHorizontal(x1.clone(), x2.clone(), "-", true);
           valid3 = verifyCoordinatesHorizontal(x1.clone(), x2.clone(), "sd", false);
        } else {
            valid1 = verifyCoordinatesVertical(x1.clone(), x2.clone(), "+", true);
            valid2 = verifyCoordinatesVertical(x1.clone(), x2.clone(), "-", true);
            valid3 = verifyCoordinatesVertical(x1.clone(), x2.clone(), "ds", false);
        }

        if(!isEmpty(x1, x2)){
            System.out.print("Error! coordinates already taken ");
            return false;
        }

        if(!valid1 || !valid2 || !valid3) {
            System.out.print("Error! You placed it too close to another one. Try again:");
            return false;
        };

        shipPositions[shipN][0] = x1[0] + x1[1];;
        shipPositions[shipN][1] =  x2[0] + x2[1];;
        shipPositions[shipN][2] = String.valueOf(length);
        shipPositions[shipN][3] = "-1";

        for(int i = Integer.parseInt(x1[0]); i <=  Integer.parseInt(x2[0]); i++){
            // if x1[1] < x2[1]
            if(Integer.parseInt(x1[1]) <= Integer.parseInt(x2[1])){
                for(int j = Integer.parseInt(x1[1]) - 1; j <= Integer.parseInt(x2[1]) - 1 ; j++){
                    table[i][j] = "O ";
                }
            } else {
                //if x1[1] > x2[1]
                for(int j = Integer.parseInt(x1[1]) - 1; j >= Integer.parseInt(x2[1]) - 1; j--){
                    table[i][j] = "O ";
                }
            }
        };


        return true;

    }
}
