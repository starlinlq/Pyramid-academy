package com.company;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        final String[] WORDS = {
                "THOMAS", "MARIA", "LESS", "CAR", "DOG", "CARPET",
        };

        ArrayList<String> aiGuessedWord = new ArrayList<>();
        Collections.addAll(aiGuessedWord, WORDS[random.nextInt(5)].split(("")));

        int remGuess = aiGuessedWord.size();
        String hint = aiGuessedWord.get(random.nextInt(aiGuessedWord.size()));

        String[] alreadyGuessed = new String[aiGuessedWord.size()];
        Arrays.fill(alreadyGuessed, "");

        String userGuess;
        int guessRight = 0;
        boolean gameOver = false;

        while(true){
            //when game ends

            if(remGuess == 0){
                System.out.println("Game over");
                gameOver = true;
            } else if (guessRight == alreadyGuessed.length){
                System.out.println("You win");
                gameOver = true;
            }

            if(gameOver){
                System.out.println("Do you want to play again? (yes or no)");
                String again = scanner.nextLine();
                //reset game if yes else break;
                if(again.equals("yes")){
                    aiGuessedWord.clear();
                    Collections.addAll(aiGuessedWord, WORDS[random.nextInt(5)].split(("")));
                    remGuess = aiGuessedWord.size();
                    alreadyGuessed = new String[aiGuessedWord.size()];
                    Arrays.fill(alreadyGuessed, "");
                    guessRight = 0;
                    hint = aiGuessedWord.get(random.nextInt(aiGuessedWord.size()));
                } else
                    break;
                gameOver= false;
            }


            //when game begins
            System.out.println("HINT: " + hint);

            for(String str : alreadyGuessed){
               if(str.equals("")){
                   System.out.print("_");
               } else
                   System.out.print(str);
            }
            System.out.print("\n");

            System.out.println("Enter guess");
            System.out.println("Rem guesses: " + remGuess);
            userGuess = scanner.nextLine().toUpperCase(Locale.ROOT);

            if(aiGuessedWord.contains(userGuess)){
               if(alreadyGuessed[aiGuessedWord.indexOf(userGuess)].equals(userGuess)){
                   System.out.println("You already guess the word");

               }else
               {
                   alreadyGuessed[aiGuessedWord.indexOf(userGuess)] = userGuess;
                   guessRight++;

               }
                continue;
            }
            remGuess--;
        }

    }
}
