package com.ocr.laz.mareu.service;

import androidx.annotation.VisibleForTesting;

import com.ocr.laz.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lazdev OCR on 10/09/2021.
 */
public abstract class DummyRoomGenerator {

    @VisibleForTesting
    private static List<Room> DUMMY_ROOMS = Arrays.asList(
        new Room("Alpha",4),
        new Room("Beta",2),
        new Room("Gamma",5),
        new Room("Delta",2),
        new Room("Epsilon",3),
        new Room("Zeta",4),
        new Room("Eta",5),
        new Room("Theta",2),
        new Room("Iota",2),
        new Room("Kappa",3)
    );

    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }
}


