package com.company;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Human human;
    private Goblin goblin;
    private Land land;
    private boolean isOver = false;

    final private Scanner scanner = new Scanner(System.in);
    final private Random random = new Random();

    Game(Human human, Goblin goblin, Land land){
        this.human = human;
        this.goblin = goblin;
        this.land = land;
    }

    public void start(){
        land.setPosition(human.position(), goblin.position());
        land.displayGrid();
        while(!isOver){
            playerTurn();
            goblinTurn();
            land.setPosition(human.position(), goblin.position());
            land.displayGrid();
            combat();
        }

    }

    public void playerTurn(){
        System.out.println("Please make a move");
        int n1 = scanner.nextInt() - 1;
        int n2  = scanner.nextInt() - 1;
        human.changePosition(n1, n2);
    }

    public void goblinTurn(){
        int n1 = random.nextInt(3);
        int n2  = random.nextInt(3);
        goblin.changePosition(n1, n2);
    }

    public void combat(){
        int[] h = human.position();
        int[] g = goblin.position();
        int round = 1;
        if(h[0] == g[0] && h[1] == g[1]){
            System.out.println("Combat initiated");
            while(goblin.health > 0 && human.health > 0){
                System.out.println("Round: " + round);
                System.out.println("Human attacked: ");
                goblin.decreaseHealth(human.attack());
                System.out.println(goblin.toString());
                System.out.println("Goblin attacked");
                human.decreaseHealth(goblin.attack());
                System.out.println(human.toString());
                round++;
            }
            if(goblin.health > human.health){
                System.out.println("Goblin wins");
                isOver = true;
            } else {
                isOver = true;
                System.out.println("Human wins");
            }
        }

    }

}
