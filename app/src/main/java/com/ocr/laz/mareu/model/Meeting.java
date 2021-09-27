package com.ocr.laz.mareu.model;

import java.util.Date;

/**
 * Created by Lazdev OCR on 08/09/2021.
 */
public class Meeting {

    private long roomId;
    private  String subject;
    private String beginDate;
    private String guest;
    private String roomName;
    //private Hour mHour;




    public Meeting(String subject, String beginDate, String guest, String roomName) {
        this.subject = subject;
        this.beginDate = beginDate;
        this.guest = guest;
        this.roomName = roomName;
        //mHour = date;
    }

    //private final Date mEndDate;
    //private final List<String> guestList;

    // public long getId() {
   //     return id;
   // }

    public String getSubject() {
        return subject;
    }

    public String getGuest() {
        return guest;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getRoomName() {
        return roomName;
    }
   //public Date getmEndDate() {
   //    return mEndDate;
   //}

   //public List<String> getGuestlist() {
   //    return guestList;
   //}

   //public Date getBeginDate() {
   //    return mBeginDate;
   //}
}

