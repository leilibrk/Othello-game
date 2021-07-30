package com.company;

import java.util.ArrayList;

/**
 * This program represents the othello game.
 * @author Leili
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to othello game!The player number 1 disks are  \u25CB and the player number 2 disks are \u25CF");
        System.out.println("Note: if your terminal's background is white the black disks turns to white and the white disks turns to black :)))");
        System.out.println("In each turn enter the position of the new disk with a space between the number of the row and the char of the column.");
        Map map=new Map(); //the main map of the game
        Player player1=new Player('\u25CB',map); //the first player(black)
        Player player2=new Player('\u25CF',map); // the second player(white)

           //Create the first 4 static disks and add them to their belong player's disks ArrayList

        Disk black1=new Disk('\u25CB',"5 D");
        player1.addDisk(black1);
        Disk black2=new Disk('\u25CB',"4 E");
        player1.addDisk(black2);
        Disk white1=new Disk('\u25CF',"4 D");
        player2.addDisk(white1);
        Disk white2=new Disk('\u25CF',"5 E");
        player2.addDisk(white2);
        ArrayList<Disk> allDisks=new ArrayList<>(); //creat an ArrayList of all the disks for updating map
        allDisks.addAll(player1.getDisks()); //add the player 1 disks to the ArrayList
        allDisks.addAll(player2.getDisks()); //add the player2 disks to the ArrayList
        map.updateMap(allDisks); //update the map
        map.printMap(); //print the map
        while(true){
            if(map.isFull()){
                //if the map is full, it means the game is finished
                break;
            }
            if(!player1.availablePosition(player2.getDisks())){
                if(!player2.availablePosition(player1.getDisks())){
                    //if their isn't any available position for both players,it means the game is finished.
                    break;
                }
            }
            System.out.println("player 1 turn: ");
            player1.playerTurn(player2.getDisks()); //player 1 turn
            System.out.println("player 2 turn: ");
            player2.playerTurn(player1.getDisks()); //player 2 turn
        }
        //Game finished
        map.printMap(); //print map
        map.printResult(); //print the result of the game
    }

}
