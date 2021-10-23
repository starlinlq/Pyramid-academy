package com.company;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

enum Difficulty {EASY, MEDIUM, HARD};



public class Main {

    static void playerVsComputer(TicTacToe game, String move, Difficulty difficulty) {
        game.setSymbol(move.equals("X") ? "O" : "X");
        game.setComputerAi(new Ai(move, game.table, difficulty, game.counter));
        while(!game.isAWinner){

            if(move.equals("X")){
                game.computerPLays();
                if(game.isAWinner) break;
            }

          while(true){
              Scanner scan = new Scanner(System.in);
              System.out.print("Enter the coordinates: ");
              if(scan.hasNextInt()) {

                  int c1 = scan.nextInt() - 1;
                  int c2 = scan.nextInt() - 1;
                  if (c1  > 2 || c1  < 0 || c2  > 2 || c2  < 0) {
                      System.out.println("Coordinates should be from 1 to 3!");
                      continue;
                  }
                  if(game.verifyEmpty(c1, c2 )){
                      game.playerPlays(c1 , c2 );
                      if(!game.isAWinner && move.equals("O")){
                          game.computerPLays();
                      }
                      break;

                  } else {
                      System.out.println("This cell is occupied! Choose another one!");
                  }
              } else {
                  System.out.println("You should enter numbers!");
              }
          }
        }


    }

    static void playerVsPlayer(TicTacToe game) {
        while(!game.isAWinner) {
            Scanner scan  = new Scanner(System.in);
            System.out.print("Enter the coordinates: ");
            if(scan.hasNextInt()){
                int c1 = scan.nextInt();
                int c2 = scan.nextInt();
                game.playerVsPlayer( c1 -1 , c2 - 1);
            } else {
                System.out.println("You should enter numbers!");
            }
        }

    }

    static void computerVsComputer(TicTacToe game, Difficulty p1, Difficulty p2) {
        game.computerVsComputer(p1, p2);
    }

    public static void main(String[] args) {
        // write your code here
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Input command: ");
            String[] commands = scan.nextLine().split(" ");
            String command = commands[0];
            if(command.equals("exit")) break;
            if(commands.length < 3 ) {
                System.out.println("Bad parameters!");
                continue;
            }
            String p1 = commands[1];
            String p2 = commands[2];
            if(command.equals("start") && p1.equals("easy") || p1.equals("medium") || p1.equals("hard") || p1.equals("user") && p2.equals("user") || p2.equals("easy")  || p2.equals("hard")|| p2.equals("medium")) {

                TicTacToe game  = new TicTacToe();
                game.setTable();
                game.printTable();

                if (p1.equals("user") && p2.equals("user")){
                    playerVsPlayer(game);
                }

                if(p1.equals("user")){
                    playerVsComputer(game,"O", Difficulty.valueOf(p2.toUpperCase(Locale.ROOT)));
                    continue;
                } else if (p2.equals("user")){
                    playerVsComputer(game, "X",Difficulty.valueOf(p1.toUpperCase(Locale.ROOT)) );
                    continue;
                }
               if(p1.equals("easy") || p1.equals("medium") || p1.equals("hard") && p2.equals("easy") || p2.equals("hard") || p2.equals("medium")){
                  computerVsComputer(game, Difficulty.valueOf(p1.toUpperCase(Locale.ROOT)), Difficulty.valueOf(p2.toUpperCase(Locale.ROOT)));
               }
            } else {
                System.out.println("Bad parameters!");
            }

        }

    }
}
