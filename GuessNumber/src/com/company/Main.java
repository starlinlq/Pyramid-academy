package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int guessLimit = 6;
        boolean gameOver = false;
        Random random = new Random();
        int aiGuessed = random.nextInt(1, 21);
        Scanner scanner = new Scanner(System.in);
        int guessCount = 0;
        while(true){
            if(gameOver){
                System.out.println("Would you like to play again? (y or n)");
                scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if(input.equals("y")){
                    aiGuessed =  random.nextInt(21);
                    guessCount = 0;
                } else
                    break;
                gameOver = false;

            }

            System.out.println("Take a guess");
            int user = scanner.nextInt();
            guessCount++;


            if(user == aiGuessed){
                System.out.println("Good job, Abaddon! You guessed my number in "+ guessCount + "guesses!");
              gameOver = true;
            } else if(guessCount == guessLimit){
                System.out.println("You loose");
                gameOver = true;
            } else if(user > aiGuessed){
                System.out.println("your guess is to high");
            }
            else {
                System.out.println("your guess is to low");
            }

        }
    }
}
