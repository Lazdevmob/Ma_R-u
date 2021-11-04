package com.ocr.laz.mareu.ui.MeetingList.AddMeetingTest;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.ocr.laz.mareu.R;
import com.ocr.laz.mareu.ui.MeetingList.AddMeetingActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTestRoom {

    @Rule
    public ActivityTestRule<AddMeetingActivity> mActivityTestRule = new ActivityTestRule<>(AddMeetingActivity.class);

    @Test
    public void addMeetingActivityTestRoomGuest() {
       ViewInteraction materialAutoCompleteTextView = onView(
               allOf(withId(R.id.textFieldRoom2),
                       childAtPosition(
                               childAtPosition(
                                       withId(R.id.textFieldRoom),
                                       0),
                               1)));
       materialAutoCompleteTextView.perform(scrollTo(), click());

       //DataInteraction materialTextView = onData(anything())
       DataInteraction materialTextView = onData(equalTo("Delta"))
               //.atPosition(3)
               //;
               .inRoot(RootMatchers.isPlatformPopup());
              //.inAdapterView(childAtPosition(
              //        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
              //        0))
              //.atPosition(4);
       materialTextView.perform(click());
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
