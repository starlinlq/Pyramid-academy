package com.company;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
	Land land = new Land();
    Human human = new Human("human", 100);
    Goblin goblin  = new Goblin("Goblin", 100);
    Game game = new Game(human,goblin,land);

    game.start();

    }
}
