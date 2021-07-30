package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class represents a player in the othello game.
 * @author Leili
 */
public class Player {
    private char diskType; //type of the player disks(white or black circles with geometric unicode)
    private ArrayList<Disk> disks; //an ArrayList of player's disks
    private Map map; //the main map of the game

    /**
     * Creates a new player with a given disk type and main map of the game
     * @param diskType a unicode represents the type of disks
     * @param map the main map
     */
    public Player(char diskType,Map map){
        this.diskType=diskType;
        this.disks=new ArrayList<>();
        this.map=map;
    }

    /**
     * Adds a new disk to the disks ArrayList
     * @param diskToAdd an object of Disk class
     */
    public void addDisk(Disk diskToAdd){
        disks.add(diskToAdd);
    }

    /**
     * Gets the ArrayList of player's disks
     * @return disks field
     */
    public ArrayList<Disk> getDisks() {
        return disks;
    }

    /**
     * Gets the type of player's disks
     * @return diskType field
     */
    public char getDiskType() {
        return diskType;
    }

    /**
     * Checks that the new disk which player wants to add is in a valid position or not.
     * The player must place the new disk in such a way that there is at least one straight(horizontal,
     * vertical,diagonal) occupied line between the new disk and another disk of the same type,with one or more
     * disks of other player(other type) between them.(This explanation is from the eothello.com website)
     * @param new_disk a new disk to add
     * @param player2_Disks an ArrayList of the other player's disks
     * @return true or false in some cases
     */
    public  boolean check_valid_position(Disk new_disk, ArrayList<Disk> player2_Disks){
        if(map.getChar(new_disk.getRow(),new_disk.getColumn())!='+'){
            //the place is occupied by another disk
            return false;
        }
        boolean b;
        for(Disk d1:player2_Disks){
            /*
                The new disk must place next to a disk of other player with distance of 1 or sqrt 2.
                (north,south,east,west,north-east,north-west,south-east,south-west)
             */
            if(d1.getColumn()==new_disk.getColumn() && d1.getRow()+1==new_disk.getRow()){
                //finds a disk of other player's diskType in the same column and in the northern part of new disk with distance of 1
                for(Disk d2:disks){
                    if(d2.getColumn()==d1.getColumn() && d2.getRow()<d1.getRow()){
                        //finds a disk of the same type in the same column and in the northern part of other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        for(int i=d2.getRow()+1;i<new_disk.getRow();i++){
                            if(map.getChar(i,new_disk.getColumn())=='+'){
                                b=false;
                            }
                        }

                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }
                    }
                }

            }
            else if(d1.getColumn()==new_disk.getColumn() && d1.getRow()-1==new_disk.getRow()){
                //finds a disk of other player's diskType in the same column and in the southern part of new disk with distance of 1
                for(Disk d2:disks){
                    if(d2.getColumn()==d1.getColumn() && d2.getRow()>d1.getRow()){
                        //finds a disk of the same type in the same column and in the southern part of the other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        for(int i=new_disk.getRow()+1;i<d2.getRow();i++){
                            if(map.getChar(i,new_disk.getColumn())=='+'){
                                b=false;
                            }
                        }
                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }
                    }
                }
            }

            else if(d1.getRow()==new_disk.getRow() && d1.getColumn()+1==new_disk.getColumn()){
                //finds a disk of other player's diskType in the same row and in the western part of new disk with distance of 1
                for(Disk d2:disks){
                    if(d2.getRow()==new_disk.getRow() && d2.getColumn()<d1.getColumn()){
                        //finds a disk of the same type in the same row and in the western part of the other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        for(int j=d2.getColumn()+1;j<new_disk.getColumn();j++){
                            if(map.getChar(new_disk.getRow(),j)=='+'){
                                b=false;
                            }
                        }
                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }

                    }
                }
            }
            else if(d1.getRow()==new_disk.getRow() && d1.getColumn()-1==new_disk.getColumn()){
                //finds a disk of other player's diskType in the same row and in the eastern part of new disk with distance of 1
                for(Disk d2:disks){
                    if(d2.getRow()==d1.getRow() && d2.getColumn()>d1.getColumn()){
                        //finds a disk of the same type in the same row and in the eastern part of the other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        for(int j=new_disk.getColumn()+1;j<d2.getColumn();j++){
                            if(map.getChar(new_disk.getRow(),j)=='+'){
                                b=false;
                            }
                        }
                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }
                    }
                }
            }

            else if(d1.getRow()+1==new_disk.getRow() && d1.getColumn()+1==new_disk.getColumn()){
                //finds a disk of other player's diskType in the same diameter and in the north-western part of new disk with distance of sqrt 2
                for(Disk d2:disks){
                    if(d2.getColumn()<d1.getColumn() && d2.getRow()<d1.getRow() && (Math.abs(d2.getColumn()-d1.getColumn())==Math.abs(d2.getRow()-d1.getRow()))){
                        //finds a disk of the same type in the same diameter and in the north-western part of the other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        int j=d2.getColumn()+1;
                        int i=d2.getRow()+1;
                        while(i<new_disk.getRow() && j<new_disk.getColumn()){
                            if(map.getChar(i,j)=='+'){
                                b=false;
                            }
                            j++;
                            i++;
                        }
                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }
                    }
                }
            }
            else if(d1.getRow()-1==new_disk.getRow() && d1.getColumn()+1==new_disk.getColumn()){
                //finds a disk of other player's diskType in the same diameter in the south-western part of new disk with distance of sqrt 2
                for(Disk d2:disks){
                    if(d2.getColumn()<d1.getColumn() && d2.getRow()>d1.getRow() && (Math.abs(d2.getColumn()-d1.getColumn())==Math.abs(d2.getRow()-d1.getRow()))){
                        //finds a disk of the same type in the same diameter and in the south-western part of the other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        int j=d2.getColumn()+1;
                        int i=d2.getRow()-1;
                        while(i>new_disk.getRow() && j<new_disk.getColumn()){
                            if(map.getChar(i,j)=='+'){
                                b=false;
                            }
                            j++;
                            i--;
                        }
                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }
                    }
                }
            }
            else if(d1.getRow()-1==new_disk.getRow() && d1.getColumn()-1==new_disk.getColumn()){
                //finds a disk of other player's diskType in the same diameter in the south-eastern part of new disk with distance of sqrt 2
                for(Disk d2:disks){
                    if(d2.getColumn()>d1.getColumn() && d2.getRow()>d1.getRow() && (Math.abs(d2.getColumn()-d1.getColumn())==Math.abs(d2.getRow()-d1.getRow()))){
                        //finds a disk of the same type in the same diameter and in the south-eastern part of the other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        int j=new_disk.getColumn()+1;
                        int i=new_disk.getRow()+1;
                        while(i<d2.getRow() && j<d2.getColumn()){
                            if(map.getChar(i,j)=='+'){
                                b= false;
                            }
                            j++;
                            i++;
                        }
                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }

                    }
                }
            }
            else if(d1.getRow()+1==new_disk.getRow() && d1.getColumn()-1==new_disk.getColumn()){
                //finds a disk of other player's diskType in the same diameter in the north-eastern part of new disk with distance of sqrt 2
                for(Disk d2:disks){
                    if(d2.getColumn()>d1.getColumn() && d2.getRow()<d1.getRow() && (Math.abs(d2.getColumn()-d1.getColumn())==Math.abs(d2.getRow()-d1.getRow()))){
                        //finds a disk of the same type in the same diameter and in the north-eastern part of the other player's disk(d1)
                        b=true;
                        //it shouldn't be any empty places between the two disks of the same type(d2 and new_disk)
                        int j=d2.getColumn()-1;
                        int i=d2.getRow()+1;
                        while(i<new_disk.getRow() && j>new_disk.getColumn()){
                            if(map.getChar(i,j)=='+'){
                                b=false;
                            }
                            j--;
                            i++;
                        }
                        if(b){
                            //the new_disk's position is valid
                            return true;
                        }
                    }
                }
            }

        }
        //the new_disk's position is invalid,So it will return false.
        return false;
    }

    /**
     * Checks the other player's disks and color the discs lying on
     * a straight line between the new disk(the main disk) and any existing disk of the same type as the main disk.
     * @param main_disk the new disk which has recently been added
     * @param player2_Disks an ArrayList of other player's disks
     */
    public void checkAndColor(Disk main_disk,ArrayList<Disk>player2_Disks){

        for (int k=0;k<disks.size();k++){
            if(disks.get(k).getColumn()==main_disk.getColumn()){
                //finds a disk of the same type in the same column as main_disk's column.
                boolean b=true;
                int row1=main_disk.getRow();
                int row2=disks.get(k).getRow();
                if(row1>row2){
                    int temp=row1;
                    row1=row2;
                    row2=temp;
                }
                //if their is any other disks of the same type as main_disk's type or empty places between the two disks, it won't call the color method.
                for(int row=row1+1;row<row2;row++){
                    if(map.getChar(row,disks.get(k).getColumn())=='+' || map.getChar(row,disks.get(k).getColumn())==diskType){
                        b=false;
                    }
                }
                if(b){
                    for (int row=row1+1;row<row2;row++){
                        //call the color method
                        color(player2_Disks,row,main_disk.getColumn());
                    }
                }

            }
            else if(disks.get(k).getRow()==main_disk.getRow()){
                //finds a disk of the same type in the same row as main_disk's row.
                boolean b=true;
                int column1=main_disk.getColumn();
                int column2=disks.get(k).getColumn();
                if(column1>column2){
                    int temp=column1;
                    column1=column2;
                    column2=temp;
                }
                //if their is any other disks of the same type as main_disk's type or empty places between the two disks, it won't call the color method.
                for(int column=column1+1;column<column2;column++){
                    if(map.getChar(disks.get(k).getRow(),column)=='+' || map.getChar(disks.get(k).getRow(),column)==diskType){
                        b=false;
                    }
                }
                if(b) {
                    for (int column = column1+1; column < column2; column++) {
                        //call the color method
                        color(player2_Disks, main_disk.getRow(), column);
                    }
                }

            }
            else if(Math.abs(main_disk.getRow()-disks.get(k).getRow())==Math.abs(main_disk.getColumn()-disks.get(k).getColumn())){
                //finds a disk of the same type in the same diameter as the main_disk's diameter
                int row1=main_disk.getRow();
                int column1=main_disk.getColumn();
                int row2=disks.get(k).getRow();
                int column2=disks.get(k).getColumn();
                if(row1>row2 && column1<column2){
                    boolean b=true;
                    int i=row2+1;
                    int j=column2-1;
                    //if their is any other disks of the same type as the main_disk's type or empty places between the two disks, it won't call the color method.
                    while(i<row1 && j>column1){
                        if(map.getChar(i,j)=='+'  || map.getChar(i,j)==diskType){
                            b=false;
                        }
                        i++;
                        j--;
                    }
                    if(b) {
                        i = row2 + 1;
                        j = column2 - 1;
                        while (i < row1 && j > column1) {
                            //call the color method
                            color(player2_Disks, i, j);
                            i++;
                            j--;
                        }
                    }
                }
                else if(row1<row2 && column1>column2){
                    boolean b=true;
                    int i=row2-1;
                    int j=column2+1;
                    //if their is any other disks of the same type as the main_disk's type or empty places between the two disks, it won't call the color method.
                    while (i>row1 && j<column1){
                        if(map.getChar(i,j)=='+'|| map.getChar(i,j)==diskType){
                            b=false;
                        }
                        i--;
                        j++;
                    }
                    if(b) {
                        i = row2 - 1;
                        j = column2 + 1;
                        while (i > row1 && j < column1) {
                            //call the color method
                            color(player2_Disks, i, j);
                            i--;
                            j++;
                        }
                    }
                }
                else if(row1>row2 && column1>column2){
                    boolean b=true;
                    int i=row2+1;
                    int j=column2+1;
                    //if their is any other disks of the same type as the main_disk's type or empty places between the two disks, it won't call the color method.
                    while (i<row1 && j<column1){
                        if(map.getChar(i,j)=='+'|| map.getChar(i,j)==diskType){
                            b=false;
                        }
                        i++;
                        j++;
                    }
                    if(b) {
                        i = row2 + 1;
                        j = column2 + 1;
                        while (i < row1 && j < column1) {
                            //call the color method
                            color(player2_Disks, i, j);
                            i++;
                            j++;
                        }
                    }
                }
                else if(row1<row2 && column1<column2){
                    boolean b=true;
                    int i=row2-1;
                    int j=column2-1;
                    //if their is any other disks of the same type as the main_disk's type or empty places between the two disks, it won't call the color method.
                    while(i>row1 && j>column1){
                        if(map.getChar(i,j)=='+'|| map.getChar(i,j)==diskType){
                            b=false;
                        }
                        i--;
                        j--;
                    }
                    if(b) {
                        i = row2 - 1;
                        j = column2 - 1;
                        while (i > row1 && j > column1) {
                            //call the color method
                            color(player2_Disks, i, j);
                            i--;
                            j--;
                        }
                    }
                }
            }
        }

    }

    /**
     * Colors the disks.
     * It will get a row number and a column number and checks that if their is any
     * disk of other player's disks in the given position, it will change the type of that disk
     * and add the disk to the disks ArrayList and also remove it from the other player's ArrayList of disks.
     * @param player2_Disks an ArrayList of other player's disks
     * @param i the row number
     * @param j the column number
     */
    public  void color(ArrayList<Disk>player2_Disks,int i,int j){
        Iterator<Disk> it=player2_Disks.iterator();
        while(it.hasNext()) {
            Disk disk1 = it.next();
            if (disk1.getRow() == i && disk1.getColumn() == j) {
                disk1.setType(diskType);
                disks.add(disk1);
                it.remove();
            }
        }
    }

    /**
     * Scan a string of position and after checking that the input is valid,
     * it will creat a new disk with the given position,and after checking that the
     * string is valid,it will call the check_valid_position to check that the disk
     * can place in the given position or not and if the position is valid,
     * it will call the checkAndColor method.
     * In case of invalid input or invalid position, it will print an error message.
     * @param player2_Disks an ArrayList of other player's disks
     */
    public void scanNewDisk(ArrayList<Disk>player2_Disks){

        Scanner a=new Scanner(System.in);
        String position=a.nextLine();
        if(position.length()!=3){
            System.out.println("Invalid input!");
            return;
        }
        Disk disk=new Disk(diskType,position);
        if(disk.getRow()>=9 || disk.getColumn()>=9 || disk.getRow()<1 || disk.getColumn()<1){
            System.out.println("Invalid input!");
            return;
        }

        if(check_valid_position(disk,player2_Disks)){
            disks.add(disk);
            checkAndColor(disk,player2_Disks);
            return;
        }

        System.out.println("Invalid position");

    }

    /**
     * Checks the available positions.
     * If their isn't any available position, it will return false.
     * @param player2_disks an ArrayList of other player's disks.
     * @return true or false
     */
    public  boolean availablePosition(ArrayList<Disk>player2_disks){

        for(int i=1;i<=8;i++){
            for(char j='A';j<='H';j++){
                String position=""+i+" "+j;
                Disk disk=new Disk(diskType,position);
                if(check_valid_position(disk,player2_disks)){
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Performs all the required methods in each turn.
     * First it will check that there is any available position for the player or not.
     * If there isn't, it will print "pass" and don't do anything else.
     * If there is at least one available position,it will scan a new disk,color some disks,update and print the map.
     * @param player2Disks an ArrayList of the other player's disk
     */
    public void playerTurn(ArrayList<Disk> player2Disks){
        if(!availablePosition(player2Disks)) {
                /*if their isn't any available position for the player1,
                    it will print "pass" and the player1 won't be able to
                    creat a new disk.
                 */
            System.out.println("pass!");
        }
        else{
            scanNewDisk(player2Disks);
            ArrayList<Disk> allDisks=new ArrayList<>();
            allDisks.addAll(disks);
            allDisks.addAll(player2Disks);
            map.updateMap(allDisks);
            map.printMap();
        }
    }

}
