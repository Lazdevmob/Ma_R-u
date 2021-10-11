package com.ocr.laz.mareu.service;

import androidx.annotation.VisibleForTesting;

import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lazdev OCR on 10/09/2021.
 */
public abstract class DummyRoomGenerator {

    @VisibleForTesting
    private static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("Alpha", 0xffff0000),
            new Room("Beta", 0xff00ffff),
            new Room("Gamma", 0xFF3F51B5),
            new Room("Delta",0xff00ff00),
            new Room("Epsilon", 0xff00aaaa),
            new Room("Zeta", R.color.black),
            new Room("Eta", 0xff0000ff),
            new Room("Theta", 0xff00ffff),
            new Room("Iota", R.color.black),
            new Room("Kappa", 0xff00aaaa)
    );

    public static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

//   public static List<String> listStringRoom = Arrays.asList(
//            "Alpha","Beta","Gamma","Delta","Epsilon","Zeta","Eta","Theta","Iota","Kappa"
//    );

    //  public static String[] generateListStringRoom() {
    //      String[] listStringRoom = new String[DUMMY_ROOMS.size()];
    //      for (int i = 0; i < DUMMY_ROOMS.size(); i++) {
    //          //String name = DUMMY_ROOMS.get(i).toString();
    //          String name = DUMMY_ROOMS.get(i).getName();
    //          listStringRoom[i] = name;
    //      }
    //      return listStringRoom;
    //  }

    public static Map<String, Integer> generateRoomHashMap() {
        Map<String, Integer> roomHashMap = new LinkedHashMap<>();
        for (Room room : DUMMY_ROOMS) {
            roomHashMap.put(room.getName(), room.getColour());
        }
        return roomHashMap;
    }
}



