package com.ocr.laz.mareu.service;

import com.ocr.laz.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lazdev OCR on 14/09/2021.
 */
public class ImplMeetingApiService implements MeetingApiService  {
    String givenDate;


//    private final List<Meeting> meetings= new ArrayList<>();
    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();


    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> getMeetingsAtGivenDate(String givenDate) {
        List<Meeting> filteredMeetingByDateList = new ArrayList<>();
        for ( Meeting m : meetings) {
            if (m.getDate().equals(givenDate)) {
                filteredMeetingByDateList.add(m);
            }
        }
        return filteredMeetingByDateList;
    }

    @Override
    public List<Meeting> getMeetingsInGivenRooms(List<String> selectedRooms) {
        return null;
    }

}
