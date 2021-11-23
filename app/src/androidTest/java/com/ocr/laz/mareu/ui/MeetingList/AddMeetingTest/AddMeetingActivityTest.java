package com.ocr.laz.mareu.ui.MeetingList.AddMeetingTest;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.model.Room;
import com.ocr.laz.mareu.repository.DummyRoomGenerator;
import com.ocr.laz.mareu.ui.MeetingList.ListMeetingActivity;
import com.ocr.laz.mareu.ui.MeetingList.utils.RecyclerViewUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTest {


    private static final int ITEMS_COUNT = 7;


    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule2 = new ActivityTestRule<>(ListMeetingActivity.class);

    @Test
    public void addMeetingActivityTest() {

        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.activity_add_meeting_fab))).perform(click());


/**
 * addMeetingActivityTestSubject
 */
        ViewInteraction textInputEditText = onView(withId(R.id.textFieldSubject2));
        textInputEditText.perform(replaceText("Reunion Test")
                , closeSoftKeyboard());

/**
 * addMeetingActivityTestDate
 */
        ViewInteraction textInputEditTextDate = onView(withId(R.id.textFieldDate2));
        textInputEditTextDate.perform(click());
        ViewInteraction materialButtonDate = onView(
                allOf(withId(android.R.id.button1),
                        isDisplayed()));
        materialButtonDate.perform(click());

/**
 * addMeetingActivityTestHour
 */
        ViewInteraction textInputEditTextHour = onView(withId(R.id.textFieldHour2));
        textInputEditTextHour.perform(scrollTo(), click());
        ViewInteraction materialButton = onView
                (allOf(withId(android.R.id.button1),
                        isDisplayed()));
        materialButton.perform(click());

/**
 * addMeetingActivityTestRoom
 */
        ViewInteraction materialAutoCompleteTextView = onView(
                withId(R.id.textFieldRoom2));
        materialAutoCompleteTextView.perform(scrollTo(), click());

        Room room = DummyRoomGenerator.generateRooms().get(3);
        DataInteraction materialTextView = onData(equalTo(room))
                .inRoot(RootMatchers.isPlatformPopup());
        materialTextView.perform(click());


/**
 * addMeetingActivityTestGuest
 */
        ViewInteraction materialAutoCompleteTextView2 = onView(withId(R.id.textFieldGuest2));
        materialAutoCompleteTextView2.perform(scrollTo(), click());
        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(withId(R.id.select_dialog_listview))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());
        ViewInteraction materialButtonGuest = onView(withId(android.R.id.button1));
        materialButtonGuest.perform(scrollTo(), click());

/**
 * addMeetingActivityTestSaveButton
 */
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.saveMeetingButton), withText("Save Meeting")));
        materialButton2.perform(click());

/**
 * addMeetingActivityTest
 */
        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT + 1));
    }
}
