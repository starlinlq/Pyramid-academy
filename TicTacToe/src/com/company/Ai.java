package com.company;

import java.util.Random;

public class Ai {
    public String[][] table;
    public String move;
    Difficulty difficulty;
    public String opponent;
    Random random;
    int counter;

    public Ai(String move, String[][] table, Difficulty difficulty, int counter) {
        this.move = move;
        this.opponent = move.equals("O") ? "X" : "O";
        this.table = table;
        this.difficulty = difficulty;
        this.random = new Random();
        this.counter = counter;

    };

    public void play(){
        switch (difficulty){
            case EASY: easyLogic(); break;
            case MEDIUM: mediumLogic(); break;
            case HARD: hardLogic(); break;
        }
    }

    public void setMove(String move) {
        this.move = move;
    }

    private void easyLogic(){
        System.out.println("Making move level \"easy\"");
        int r1;
        int r2;
        while(true){
            r1 = random.nextInt(3);
            r2 = random.nextInt(3);
            if(table[r1][r2].equals(" ")){
                table[r1][r2] = move;
                break;
            }
        }
    }

    private boolean makeMoveHorizontal(String prev1, String prev2, String prev3, int i, int j, String move) {
        if(prev1.equals(move)  && prev2.equals(move)  && prev3.equals(" ")) {
            table[i][j] = this.move;
           return true;
        } else if (prev3.equals(move) && prev2.equals(move)  && prev1.equals(" ")) {
            table[i][0] = this.move;
            return true;
        }
        return false;
    }

    private boolean makeMoveVertical(String prev1, String prev2, String prev3, int i, int j, String move){
        if(prev1.equals(move)  && prev2.equals(move)  && prev3.equals(" ")) {
            table[j][i] = this.move;
            return true;
        } else if (prev3.equals(move) && prev2.equals(move)  && prev1.equals(" ")) {
            table[0][i] = this.move;
            return true;
        }

        return false;
    }

    private boolean makeMoveDiagonal(String move){
        if(table[0][2].equals(move)   && table[1][1].equals(move)  && table[2][0].equals(" ")){
            table[2][0] = this.move;
           return true;
        } else if (table[2][0].equals(move)    && table[1][1].equals(move)  && table[0][2].equals(" ")){
            table[0][2] = this.move;
          return true;
        } else if (table[0][0].equals(move)  && table[1][1].equals(move) && table[2][2].equals(" ")){
            table[2][2] = this.move;
            return true;
        } else if (table[2][2].equals(move)  && table[1][1].equals(move)  && table[0][0].equals(" ")){
            table[0][0] = this.move;
            return true;
        }

        return false;
    }



    private void hardLogic() {
        int bestScore = Integer.MIN_VALUE;
        System.out.println("Making move level \"hard\"");
        int[] bestMove = new int[]{-1, -1};

        for(int i = 0; i < table.length; i++) {

            for(int j = 0; j < table.length; j++) {
                if(table[i][j].equals(" ")){
                    table[i][j] = move;
                    int score = minimax(table, 0 ,false);
                    table[i][j] = " ";
                    if(score > bestScore){
                       bestMove[0] = i;
                       bestMove[1] = j;
                       bestScore = score;
                    }
                }
            }
        }

        table[bestMove[0]][bestMove[1]] = move;

    }

    private int minimax(String[][] table, int depth, boolean isMaximizing) {
        String human = move.equals("O") ? "X" : "O";
        boolean ai = verifyWinner(move);
        boolean h = verifyWinner(human);
        boolean tie = checkTie();

        if(ai){
            return 1;
        } else if(h) {
            return -1;
        } else if (tie){
            return 0;
        }

        if(isMaximizing){
            int highestVal = Integer.MIN_VALUE;
            for(int i = 0; i < table.length; i++) {
                for(int j = 0; j < table.length; j++) {
                    if(table[i][j].equals(" ")){
                        table[i][j] = move;
                        int score = minimax(table, depth + 1, false);
                        highestVal = Math.max(highestVal, score);
                        table[i][j] = " ";
                    }
                }
            }
            return highestVal;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for(int i = 0; i < table.length; i++) {
                for(int j = 0; j < table.length; j++) {
                    if(table[i][j].equals(" ")) {
                        table[i][j] = human;
                        int score = minimax(table, depth + 1, true);
                        lowestVal = Math.min(lowestVal, score);
                        table[i][j] = " ";
                    }
                }
            }
            return lowestVal;
        }
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

    public boolean checkTie(){
        int counter = 0;
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table.length; j++){
                if(table[i][j].equals(" ")){
                    counter++;
                }
            }
        }
        return counter == 0;
    }

    public boolean verifyWinner(String winner) {
        var w1 = checkWinner1(winner);
        var w2 = checkWinner2(winner);
        var w3 = checkWinner3(winner);


        if (w1 || w2 || w3) {
            return true;
        }
        return false;
    }


    private void mediumLogic() {
        String prev1 = "";
        String prev2 = "";
        String prev3 = "";
        boolean madeMove = false;
        System.out.println("Making move level \"medium\"");
        // check horizontal;
        for (int i = 0; i < table.length; i++){
            for(int j = 0; j <= table.length; j++){
                if(j == 0) prev1 = table[i][j];
                if(j == 1) prev2 = table[i][j];
                if(j == 2) prev3 = table[i][j];
                if(j == 2) {
                  if(makeMoveHorizontal(prev1, prev2, prev3, i, j, move) || makeMoveHorizontal(prev1, prev2, prev3, i, j , opponent)){
                      madeMove = true;
                      break;
                  }
                }
            }
            if(madeMove) break;

            // check vertical
            for (int  j = 0; j < table.length; j++) {

                if(j == 0) prev1 = table[j][i];
                if(j == 1) prev2 = table[j][i];
                if(j == 2) prev3 = table[j][i];
                if(j == 2){

                    if(makeMoveVertical(prev1, prev2, prev3, i, j, move) || makeMoveVertical(prev1, prev2, prev3, i, j, opponent)){
                        madeMove = true;
                        break;
                    }
                }
            }

            if(madeMove) break;

            if(makeMoveDiagonal(move) || makeMoveDiagonal(opponent)) break;

            if(i == 2){
              while(true){
                  int m1 = random.nextInt(3);
                  int m2 = random.nextInt(3);
                  if(table[m1][m2].equals(" ")){
                      table[m1][m2] = move;
                      break;
                  }
              }
              break;
            }
        }
    }


}
