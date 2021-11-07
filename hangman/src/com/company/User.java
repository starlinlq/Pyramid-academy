package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable, Comparable<User> {
    private String name;
    private int score;


    User(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(User o) {
      if(this.getScore() > o.getScore()){
          return -1;
      } else if(this.getScore() < o.getScore()){
          return 1;
      }
      return 0;
    }
}
