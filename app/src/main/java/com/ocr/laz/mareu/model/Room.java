package com.ocr.laz.mareu.model;

/**
 * Created by Lazdev OCR on 08/09/2021.
 */
public class Room {
    private String name;
    private int NbPlaces;

    public Room(String name, int nbPlaces) {
        this.name = name;
        NbPlaces = nbPlaces;
    }

    public String getName() {
        return name;
    }


    public int getNbPlaces() {
        return NbPlaces;
    }

}
