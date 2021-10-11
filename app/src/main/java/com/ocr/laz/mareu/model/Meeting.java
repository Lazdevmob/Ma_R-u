package com.ocr.laz.mareu.model;

/**
 * Created by Lazdev OCR on 08/09/2021.
 */
public class Meeting {

    private long roomId;
    private  String subject;
    private String beginHour;
    private String date;
    private String guest;
    private String roomName;

    public Meeting(String subject, String beginHour, String date, String guest, String roomName) {
        this.subject = subject;
        this.beginHour = beginHour;
        this.date = date;
        this.guest = guest;
        this.roomName = roomName;
    }

    public String getSubject() {
        return subject;
    }

    public String getBeginHour() {
        return beginHour;
    }

    public String getDate() {
        return date;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getGuest() {
        return guest;
    }
}

