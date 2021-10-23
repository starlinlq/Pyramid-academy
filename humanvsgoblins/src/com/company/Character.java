package com.company;

abstract public class Character {
    protected String name;
    protected int health;
    protected int[] position;

    Character(String name, int health, int[] position){
        this.name = name;
        this.health = health;
        this.position = position;
    }

    public void changePosition(int n1, int n2){
      position[0] = n1;
      position[1] = n2;

    }
    public int[] position(){
        return position;
    }

    public void decreaseHealth(int damageTaken){
        this.health -= damageTaken;
    }

    abstract int attack();
}
