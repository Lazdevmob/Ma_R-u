package com.ocr.laz.mareu.service;

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
     *
     * @param meeting
     */

    void deleteMeeting(Meeting meeting);


    /**
     * create a meeting
     *
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    List<Meeting> getMeetingsAtGivenDate(String givenDate);

    List<Meeting> getMeetingsInGivenRooms(List<String> selectedRooms);
}
