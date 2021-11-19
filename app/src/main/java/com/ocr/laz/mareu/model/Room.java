package com.ocr.laz.mareu.model;

/**
 * Model object representing a Room
 */
public class Room {

    private String name;
    private int colour;

    public Room(String name, int colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public int getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return name;
    }

}
