package com.ocr.laz.mareu.repository;

/**
 * Created by Lazdev OCR on 10/09/2021.
 */

import com.ocr.laz.mareu.model.Meeting;

import java.util.List;

/**
 * Meeting API client
 */
public interface MeetingApiService {

    /**
     * get all meeting return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * delete a meeting
     *      *
     * @param meeting
     */

    void deleteMeeting(Meeting meeting);


    /**
     * create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);


    /**
     * * Filter meetings by date
     * @param givenDate
     */
    List<Meeting> getMeetingsAtGivenDate(String givenDate);


    /**
     * Filter meetings by room
     * @param selectedRooms
     */
    List<Meeting> getMeetingsInGivenRooms(List<String> selectedRooms);
}
