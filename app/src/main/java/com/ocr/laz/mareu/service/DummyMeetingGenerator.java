package com.ocr.laz.mareu.service;

import com.ocr.laz.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lazdev OCR on 10/09/2021.
 */
public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion 1","14h00","Mon 27/09/21", "Dede", "Alpha"),
            new Meeting("Reunion 2","14h00", "Tue 28/09/21", "Deda", "Beta"),
            new Meeting("Reunion 3","14h00", "Wed 29/09/21", "Dedi", "Gamma"),
            new Meeting("Reunion 4","14h00", "Thu 30/09/21", "Dedu", "Delta"),
            new Meeting("Reunion 5","14h00", "Fri 01/10/21", "Dedy", "Epsilon"),
            new Meeting("Reunion 6","14h00", "Mon 04/10/21", "Deze", "Zeta"),
            new Meeting("Reunion 7","14h00", "Tue 05/10/21", "Dedo", "Eta")
            );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }


}
