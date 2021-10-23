package com.company;

public class Land {
    String[][] grid = new String[3][3];

    private void createGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j]= "";
            }
        }
    }

    public void displayGrid(){
        System.out.println("- 1 - 2 - 3 -");
        for(int i = 0; i < grid.length; i++){
            System.out.print("|");
            for (int j = 0; j < grid.length; j++){
                if(j == 2){
                    if(grid[i][j].equals("")){
                        System.out.print(" _ | "+(i+1)+"\n");
                    }else
                        System.out.print(grid[i][j]+"|" + "\n");

                } else {
                    if(grid[i][j].equals("")){
                        System.out.print(" _ |");
                    } else
                        System.out.print(grid[i][j]+"|");
                }
            }
        }
        System.out.println("-------------");
    }

    public void setPosition(int[] human, int[] goblin){
        createGrid();
        if(human[0] == goblin[0] && human[1] == goblin[1]){
            grid[human[0]][human[1]] = " HG ";
        } else {
            grid[human[0]][human[1]] = " H ";
            grid[goblin[0]][goblin[1]] = " G ";
        }

    }
}
