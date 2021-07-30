package com.company;

import java.util.ArrayList;

/**
 * This class represents the main map of the game.
 */
public class Map {
    private char[][] map; //a 2D char array which represents the main map of the game
    public Map(){
        this.map=new char[9][9];
        setMap();
    }

    /**
     * Sets the map of the game.
     */
    public void setMap(){
        int i=0;
        int j=0;
        map[i][j]=' ';
        for(int k=1;k<9;k++){
            map[i][k]=(char)('A'+k-1);
        }
        j=0;
        for(i=1;i<9;i++){
            map[i][j]=(char)('0'+i);
        }
        for(int k=1;k<9;k++){
            for(int h=1;h<9;h++){
                map[k][h]='+';
            }
        }


    }

    /**
     * Updates the map.
     * It will check the ArrayList of disks and place the disk's type in their places in the map.
     * @param allDisks an ArrayList of all disks
     */
    public  void updateMap(ArrayList<Disk> allDisks){
        setMap();
        for(Disk d1:allDisks){
            int row=d1.getRow();
            int column=d1.getColumn();
            map[row][column]=d1.getType();
        }
    }

    /**
     * Checks that the map is full or not.
     * if it is full,it will return true.
     * else,it will return false.
     * @return true or false
     */
    public boolean isFull(){
        for(int i=1;i<9;i++){
            for(int j=1;j<9;j++){
                if(map[i][j]=='+'){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the map of the game.
     */
    public  void printMap(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
                System.out.print("    ");
            }
            System.out.println();
        }
    }

    /**
     * Prints the result of the game;including the number of white and black disks and also the winner.
     */
    public  void printResult(){
        int w=0;
        int b=0;
        for(int i=1;i<9;i++){
            for(int j=1;j<9;j++){
                if(map[i][j]=='\u25CF'){
                    w++;
                }
                else if(map[i][j]=='\u25CB'){
                    b++;
                }
            }
        }
        if(w>b){
            System.out.println("Game finished!The player2 is the winner!");
            System.out.println("\u25CF disks: "+w+"  \u25CB disks: "+b);
        }
        else if(w<b){
            System.out.println("Game finished!The player1 is the winner!");
            System.out.println("\u25CF disks: "+w+"  \u25CB disks: "+b);
        }
        else{
            System.out.println("Game finished!The scores are equal!");
            System.out.println("\u25CF disks: "+w+"  \u25CB disks: "+b);
        }
    }

    /**
     * Gets the char in the given place
     * @param i row number
     * @param j column number
     * @return the char in the given position
     */
    public char getChar(int i, int j){
        return map[i][j];
    }
}
