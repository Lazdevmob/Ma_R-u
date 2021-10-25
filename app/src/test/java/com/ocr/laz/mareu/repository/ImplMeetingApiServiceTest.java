package com.ocr.laz.mareu.repository;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.ocr.laz.mareu.di.Di;
import com.ocr.laz.mareu.model.Meeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
//import org.hamcrest.collection.IsIterableContainingInAnyOrder;

/**
 * Created by Lazdev OCR on 21/10/2021.
 */
@RunWith(JUnit4.class)
public class ImplMeetingApiServiceTest {

    private static final int POSITION_ITEM = 0;
    private MeetingApiService service;
    //private final String GIVENDATE = "Mon 27/09/21";
    private final String GIVENDATE2 = "Wed 22/09/21";
    //private final String GIVENROOM = "Alpha";
    public static final List<String> GIVENROOM = Arrays.asList("Alphaa", "Betaa");


    @Before
    public void setup() {
        service = Di.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        //assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
        assertTrue(meetings.containsAll(expectedMeetings));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(POSITION_ITEM);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting addMeeting = new Meeting("Reunion 8", "14h00", "Mon 22/10/21", "Moi@dev.com", "Theta");
        service.createMeeting(addMeeting);
        List<Meeting> meetings = service.getMeetings();
        assertTrue(meetings.contains(addMeeting));
    }

    @Test
    public void getMeetingsAtGivenDateWithSuccess() {
        Meeting meetingTest = service.getMeetings().get(POSITION_ITEM);
        // TODO ligne optionnelle, sinon utilisation constante
        // String givenDate = meetingTest.getDate();

        //List<Meeting> meetingByDateList = service.getMeetingsAtGivenDate(GIVENDATE);
        List<Meeting> meetingByDateList = service.getMeetingsAtGivenDate(meetingTest.getDate());
        assertEquals(meetingTest, meetingByDateList.get(POSITION_ITEM));

        List<Meeting> meetingByDateList2 = service.getMeetingsAtGivenDate(GIVENDATE2);
        assertTrue(meetingByDateList2.isEmpty());


    }

    @Test
    public void getMeetingsInGivenRoomsWithSuccess() {
        Meeting meetingTest1 = service.getMeetings().get(POSITION_ITEM);
        Meeting meetingTest2 = service.getMeetings().get(POSITION_ITEM + 1);

        // List<Meeting> meetingTestList = new ArrayList<>();
        // meetingTestList.add(meetingTest1);
        // meetingTestList.add(meetingTest2);

        List<Meeting> meetingTestList = Arrays.asList(meetingTest1, meetingTest2);

        List<String> givenRoom = Arrays.asList(meetingTest1.getRoomName(), meetingTest2.getRoomName());
        List<Meeting> meetingByRoomList = service.getMeetingsInGivenRooms(givenRoom);

        assertEquals(meetingTestList, meetingByRoomList);
        //assertArrayEquals(meetingTestList.toArray(),meetingByRoomList.toArray());

        List<Meeting> meetingByRoomList2 = service.getMeetingsInGivenRooms(GIVENROOM);
        assertTrue(meetingByRoomList2.isEmpty());

    }
}