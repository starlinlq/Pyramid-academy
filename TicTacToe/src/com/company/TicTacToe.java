package com.company;
class TicTacToe {
    String[][] table = new String[3][3];

    int counter =  9;
    boolean isAWinner = false;
    String user = "X";
    String computer;
    String move = "X";
    Ai computerAi;

    public void setTable() {

        for(int x = 0; x < table.length; x++) {

            for(int j = 0; j < table.length; j++) {
                table[x][j] = " ";

            }
        }
    }

    public void printTable() {
        System.out.println("-".repeat(9));

        for(int x  = 0; x < table.length; x++){
            System.out.print("| ");
            for(int j = 0; j < table.length; j++){
                if(j == table.length -1){
                    System.out.print(table[x][j] +  " |" + "\n");
                    continue;
                }
                System.out.print(table[x][j] + " ");
            }
        }
        System.out.println("-".repeat(9));
    }

    public void setSymbol(String s) {
        this.user = s;
    }

    public void setComputerAi(Ai ai){
        this.computerAi = ai;
    }

    public void  computerPLays(){
        computerAi.play();
        decCounter();
        printTable();
        verifyWinner(computerAi.move);
    }

    public void computerVsComputer(Difficulty a1, Difficulty a2){
        Ai ai1 = new Ai("X", this.table, a1, counter);
        Ai ai2 = new Ai("O", this.table, a2, counter);
        while(!isAWinner) {
            ai1.play();
            this.decCounter();
            this.printTable();
            verifyWinner(ai1.move);
            if(isAWinner) break;
            ai2.play();
            this.decCounter();
            this.printTable();
            verifyWinner(ai2.move);
            if(isAWinner) break;
        }
    }

    private void decCounter(){
        this.counter--;
    }

    public void playerVsPlayer( int l1, int l2){

        if (l1 > 2 || l1 < 0 || l2 > 2 || l2 < 0) {
            System.out.println("Coordinates should be from 1 to 3!");
            return;
        }
        if(table[l1][l2].equals(" ")) {
            table[l1][l2] = move;
            printTable();
           decCounter();
            verifyWinner(move);
        } else {
            System.out.println("This cell is occupied! Choose another one!");
        }
        if(this.move.equals("X")){
            this.move = "O";
        } else {
            this.move = "X";
        }
    }


    public boolean verifyEmpty(int l1, int l2){
        return table[l1][l2].equals(" ");
    }

    public void playerPlays(int l1, int l2){
        play(l1, l2);
        decCounter();
        verifyWinner(this.user);
    }



    public boolean checkWinner1(String winner) {

        boolean finished = false;

        for(int x = 0; x < table.length; x++){
            if(table[x][0].equals(winner) && table[x][1].equals(winner) && table[x][2].equals(winner)){
                finished = true;
            }
            if(finished) break;
        }
        return finished;

    }

    public boolean checkWinner2(String winner){

        boolean finished = false;

        for(int x = 0; x < table.length; x++){
            if(table[0][x].equals(winner) && table[1][x].equals(winner) && table[2][x].equals(winner)){
                finished = true;
            }
            if(finished) break;
        }
        return finished;

    }

    public boolean checkWinner3(String winner){
        // add the logic for the winner
        if(table[0][2].equals(winner) && table[1][1].equals(winner) && table[2][0].equals(winner)){
            return true;
        } else return table[0][0].equals(winner) && table[1][1].equals(winner) && table[2][2].equals(winner);


    }

    public void verifyWinner(String winner) {
        var w1 = checkWinner1(winner);
        var w2 = checkWinner2(winner);
        var w3 = checkWinner3(winner);

        if (w1 || w2 || w3) {
            System.out.println(winner + " wins");
            isAWinner = true;

        }
        if (counter == 0) {
            System.out.println("Draw");
            isAWinner= true;

        }

    }

    public String verifyWinnerTest(String winner) {
        var w1 = checkWinner1(winner);
        var w2 = checkWinner2(winner);
        var w3 = checkWinner3(winner);

        if (w1 || w2 || w3) {
            System.out.println(winner + " wins");
            return winner;
        }
        if (counter == 0) {
            System.out.println("Draw");
            return "Draw";
        }
        return null;
    }


    public void play(int l1, int l2) {
        table[l1][l2] = this.user;
        printTable();
    }

}