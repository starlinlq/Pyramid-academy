package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons. In front of you");
        System.out.println("you see two caves. In one cave, the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on sight.");
        System.out.println("Which cave will you go into? (1 or 2)");
        Scanner scanner  = new Scanner(System.in);
        int n = scanner.nextInt();

        if(n < 0 || n > 3 ){
            System.out.println("Wrong cave");
        } else if(n == 1){
            System.out.println("You approach the cave...");
            System.out.println("It is dark and spooky...");
            System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
            System.out.println("Gobbles you down in one bite!");
        } else {
            System.out.println("You are safe");
        }

    }
}
