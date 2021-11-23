package com.ocr.laz.mareu.ui.MeetingList.ListMeetingTest;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ocr.laz.mareu.ui.MeetingList.utils.RecyclerViewUtils.clickChildView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;

import android.widget.DatePicker;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.di.Di;
import com.ocr.laz.mareu.repository.MeetingApiService;
import com.ocr.laz.mareu.ui.MeetingList.ListMeetingActivity;
import com.ocr.laz.mareu.ui.MeetingList.utils.RecyclerViewUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListMeetingActivityTest {
    private static final int ITEMS_COUNT = 7;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.recyclerview), isDisplayed()));
        onView(withId(R.id.recyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void checkIfRemoveMeetingIsWorking() {
        onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildView(R.id.item_list_meeting_delete_button)));
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myAddMeetingButton_selectAction_shouldDisplayAddMeetingScreen() {
        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_add_meeting_fab)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.activity_add_meeting_fab))).perform(click());
        onView(withId(R.id.add_meetings)).check(matches(isDisplayed()));
    }

    @Test
    public void filterByDateTest() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Plus d'options"),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("filter by date"),
                        isDisplayed()));
        materialTextView.perform(click());

        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 9, 27));

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1),
                        isDisplayed()));
        materialButton.perform(click());

        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT - 6));
    }

    @Test
    public void filterByRoomTest() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Plus d'options"),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("filter by room"),
                        isDisplayed()));
        materialTextView.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview)))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview)))
                .atPosition(4);
        appCompatCheckedTextView2.perform(click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview)))
                .atPosition(6);
        appCompatCheckedTextView3.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("ok")));
        materialButton.perform(scrollTo(), click());

        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT - 4));
    }

    @Test
    public void filterResetTest() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Plus d'options"),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("reset"),
                        isDisplayed()));
        materialTextView.perform(click());

        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT - 1));
    }
}

