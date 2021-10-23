package com.company;

import java.util.Random;

public class Human extends Character {

    Human(String name, int health){
        super(name,health, new int[]{1,1});
    }

    @Override
    public String toString(){
        return "name: " +this.name+"\n" + "health: "+this.health+ "\n";
    }

    @Override
    public int attack(){
        Random random = new Random();
        return random.nextInt(45) + 1;
    }

    public void displayInventory(){

    }
}
