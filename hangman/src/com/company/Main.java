package com.company;
import java.io.*;
import java.util.*;


public class Main {
    static String path = "/Users/starlinlq/Desktop/Untitled/hangman/src/com/company/users.ser";
    static List<User> list = new ArrayList<>();

    public static void writeObj(){
        try{
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(list);
            out.close();
            file.close();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void readObj(){
        try{
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);
            list = (List<User>) in.readObject();
            file.close();
            in.close();
        } catch(IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        final String[] WORDS = {
                "THOMAS", "CAR", "DOG", "CARPET",
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
        readObj();
        System.out.println("Please enter name");
        String name = scanner.nextLine();
        User user = new User(name, 0);
        int opt = -1;
        while(true) {
            //when game ends
            System.out.println("1. Play \n" + "2. ScoreBoard: \n"+ "0. exit");
            try{
               opt = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
            if (opt == 2) {
                System.out.println("");
                System.out.println("ScoreBoard");
                Collections.sort(list);
                for (User usr : list) {
                    System.out.println("name: " + usr.getName() + " score: " + usr.getScore());
                }
                System.out.println("");

            } else if (opt == 1) {
                while(true){
                    if (remGuess == 0) {
                        System.out.println("Game over");
                        writeObj();
                        gameOver = true;
                    } else if (guessRight == alreadyGuessed.length) {
                        System.out.println("You win");
                        user.setScore(user.getScore() + 600);
                        list.add(user);
                        writeObj();
                        gameOver = true;
                    }

                    if (gameOver) {
                        System.out.println("Do you want to play again? (yes or no)");
                        String again = scanner.nextLine();
                        //reset game if yes else break;
                        if (again.equals("yes")) {
                            aiGuessedWord.clear();
                            Collections.addAll(aiGuessedWord, WORDS[random.nextInt(5)].split(("")));
                            remGuess = aiGuessedWord.size();
                            alreadyGuessed = new String[aiGuessedWord.size()];
                            Arrays.fill(alreadyGuessed, "");
                            guessRight = 0;
                            hint = aiGuessedWord.get(random.nextInt(aiGuessedWord.size()));
                        } else
                            break;
                        gameOver = false;
                    }


                    //when game begins
                    System.out.println("HINT: " + hint);

                    for (String str : alreadyGuessed) {
                        if (str.equals("")) {
                            System.out.print("_");
                        } else
                            System.out.print(str);
                    }
                    System.out.print("\n");

                    System.out.println("Enter guess");
                    System.out.println("Rem guesses: " + remGuess);
                    userGuess = scanner.nextLine().toUpperCase(Locale.ROOT);

                    if (aiGuessedWord.contains(userGuess)) {
                        if (alreadyGuessed[aiGuessedWord.indexOf(userGuess)].equals(userGuess)) {
                            System.out.println("You already guess the word");

                        } else {
                            alreadyGuessed[aiGuessedWord.indexOf(userGuess)] = userGuess;

                            guessRight++;

                        }
                        continue;
                    }
                    remGuess--;
                }
            } else if (opt == 0){
                break;
            }

        }

    }
}
