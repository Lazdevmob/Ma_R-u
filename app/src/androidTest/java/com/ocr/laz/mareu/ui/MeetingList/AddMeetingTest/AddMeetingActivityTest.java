package com.ocr.laz.mareu.ui.MeetingList.AddMeetingTest;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.ui.MeetingList.AddMeetingActivity;
import com.ocr.laz.mareu.ui.MeetingList.ListMeetingActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTest {

    @Rule
    public ActivityTestRule<AddMeetingActivity> mActivityTestRule = new ActivityTestRule<>(AddMeetingActivity.class);

    @Test
    public void addMeetingActivityTest() {

/**
 * addMeetingActivityTestSubject
 */
        ViewInteraction textInputEditText = onView(withId(R.id.textFieldSubject2));
        textInputEditText.perform(replaceText("Reunion Test")
                //, closeSoftKeyboard()
        );
        //textInputEditText.perform(pressImeActionButton());

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
 * addMeetingActivityTestGuest
 */
        ViewInteraction materialAutoCompleteTextView = onView(withId(R.id.textFieldGuest2));
        materialAutoCompleteTextView.perform(scrollTo(), click());
        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(withId(R.id.select_dialog_listview))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());
        ViewInteraction materialButtonGuest = onView(withId(android.R.id.button1));
        materialButtonGuest.perform(scrollTo(), click());


        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.saveMeetingButton), withText("Save Meeting")));
        materialButton2.perform(click());

     //   onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
    }
}
