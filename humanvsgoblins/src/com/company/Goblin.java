package com.company;

import java.util.Random;

public class Goblin extends Character  {

    Goblin(String name, int health){
        super(name, health, new int[]{0,0});
    }

    @Override
    public int attack(){
        Random random = new Random();
        return random.nextInt(45) + 1;
    };

    @Override
    public String toString(){
        return "Name: " + this.name + "\n" + "Health:  " + this.health + "\n";
    }
}
