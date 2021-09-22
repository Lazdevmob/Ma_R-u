package com.ocr.laz.mareu.model;

/**
 * Created by Lazdev OCR on 08/09/2021.
 */
public class Meeting {

    private long id;
    private  String subject;
    private String beginDate;
    private String guest;
    //private long roomId;
    private String roomName;



    public Meeting(String subject, String beginDate, String guest, String roomName) {
        this.subject = subject;
        this.beginDate = beginDate;
        this.guest = guest;
        this.roomName = roomName;
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

