package battleship;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    static String path = "/Users/starlinlq/Desktop/Untitled/Caesar Cipher/src/battleship/message.txt";

    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
	File file = new File(path);

    if(!file.exists()){
       try{
           if(file.createNewFile()){
               System.out.println("File created");
           }
       } catch(IOException ex){
           System.out.println(ex.getMessage());
       }
    }


    String opt = "";

    while(!opt.equals("exit")){
         System.out.println("Do you wish to encrypt or decrypt a message?");
         opt = scanner.nextLine();

        switch (opt) {
            case "encrypt" -> {
                System.out.println("Enter your message:");
                String message = scanner.nextLine();
                System.out.println("Enter the key number (1-52)");
                try {
                    int key = Integer.parseInt(scanner.nextLine());
                    System.out.println(key);
                    encode(message, key);
                } catch (NumberFormatException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            case "decrypt" -> {
                System.out.println("Enter your message:");
                String message = scanner.nextLine();
                System.out.println("Enter the key number (1-52)");
                int key = Integer.parseInt(scanner.nextLine());
                decode(key);
            }
        }
    }

    }

    public static void decode(int key){

        try (FileReader reader = new FileReader(path)){
           int ch =  reader.read();
           while(ch != -1){
               System.out.print((char) ( ch - key));
               ch =  reader.read();
           }
            System.out.println("\n");

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void writeFile(char[] text){
        try(FileWriter writer = new FileWriter(path)){
            writer.write(text, 0, text.length);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    };

    public static void encode(String text, int key){
        char[] encoded = new char[text.length()];
        int i = 0;

        for(char ch: text.toCharArray()){
           if(ch != 32){
               ch = (char) (ch + key);
           }
           encoded[i] = ch;
           i++;
        }

        System.out.println("Your translated text is:");
        for(char ch: encoded){
            System.out.print(ch);
        }
        System.out.println("\n");

        writeFile(encoded);
    }


}
