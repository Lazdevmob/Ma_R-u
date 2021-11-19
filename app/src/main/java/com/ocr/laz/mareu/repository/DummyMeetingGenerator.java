package com.ocr.laz.mareu.repository;

import com.ocr.laz.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lazdev OCR on 10/09/2021.
 */
public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion 1","14.00", "lun. 27/09/21","Luc@lamzone.fr, Viviane@lamzone.fr", "Alpha"),
            new Meeting("Reunion 2","14.00", "mar. 28/09/21", "Quentin@lamzone.fr, Paul@lamzone.fr", "Beta"),
            new Meeting("Reunion 3","14.00", "mer. 29/09/21", "Amandine@lamzone.fr, Maxime@lamzone.f", "Gamma"),
            new Meeting("Reunion 4","14.00", "jeu. 30/09/21", "Viviane@lamzone.fr, Alexandra@lamzone.fr", "Delta"),
            new Meeting("Reunion 5","14.00", "ven. 01/10/21", "Paul@lamzone.fr, Luc@lamzone.fr", "Epsilon"),
            new Meeting("Reunion 6","14.00", "lun. 04/10/21", "Maxime@lamzone.fr, Quentin@lamzone.fr", "Zeta"),
            new Meeting("Reunion 7","14.00", "mar. 05/11/21", "Amandine@lamzone.fr, Denis@lamzone.fr", "Eta")
            );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }


}
