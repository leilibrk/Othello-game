package com.company;

/**
 * This class represents a disk in othello game.
 * @author Leili
 */
public class Disk {
    private char type; //the type of the disk(white or black circles with geometric unicode)
    private int row; //the row number of the disk
    private int column; //the column number of the disk

    /**
     * Creates a new disk with a given type and a string of the position.
     * It will convert the given string to the row number and column number.
     * @param type a unicode represents the disk type
     * @param position a string represents the disk position
     */
    public Disk(char type,String position){
        this.type=type;
        this.row=position.charAt(0)-48;
        this.column=position.charAt(2)-64;
    }

    /**
     * Sets the type of the disk
     * @param type the type of the disk
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * Gets the column of the disk
     * @return column field
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the row of the disk
     * @return row field
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the type of the disk
     * @return type field
     */
    public char getType() {
        return type;
    }
}
