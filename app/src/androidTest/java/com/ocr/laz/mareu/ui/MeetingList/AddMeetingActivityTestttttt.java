package com.ocr.laz.mareu.ui.MeetingList;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ocr.laz.mareu.ui.MeetingList.utils.RecyclerViewUtils.clickChildView;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.ui.MeetingList.utils.RecyclerViewUtils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTestttttt {

    @Rule
    public ActivityTestRule<AddMeetingActivity> mActivityTestRule = new ActivityTestRule<>(AddMeetingActivity.class);

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule2 = new ActivityTestRule<>(ListMeetingActivity.class);



    @Test
    public void addMeetingActivityTestttttt() {
        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.activity_add_meeting_fab))).perform(click());


        ViewInteraction textInputEditText = onView(withId(R.id.textFieldSubject2));
        textInputEditText.perform(scrollTo(), replaceText("Reunion Test"), closeSoftKeyboard());


       // ViewInteraction textInputEditText = onView(
       //         allOf(withId(R.id.textFieldSubject2),
       //                 childAtPosition(
       //                         childAtPosition(
       //                                 withId(R.id.textFieldSubject),
       //                                 0),
       //                         0)));
       // textInputEditText.perform(scrollTo(), replaceText("dfg"), closeSoftKeyboard());
//
       // ViewInteraction textInputEditText2 = onView(
       //         allOf(withId(R.id.textFieldSubject2), withText("dfg"),
       //                 childAtPosition(
       //                         childAtPosition(
       //                                 withId(R.id.textFieldSubject),
       //                                 0),
       //                         0)));
       // textInputEditText2.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.saveMeetingButton), withText("Save Meeting"),
                        childAtPosition(
                                allOf(withId(R.id.add_meetings),
                                        childAtPosition(
                                                withId(R.id.add_scrollmeetings),
                                                0)),
                                5)));
        materialButton.perform(scrollTo(), click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
