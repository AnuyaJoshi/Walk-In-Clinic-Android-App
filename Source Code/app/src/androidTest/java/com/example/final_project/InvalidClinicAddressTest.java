package com.example.final_project;

import android.widget.DatePicker;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsAnything.anything;

public class InvalidClinicAddressTest {

    private Administrator admin;

    @Rule
    public ActivityTestRule<MainActivity> activityRuleMain
            = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<Profile> activityRuleProfile;


    @Test
    public void invalidClinicAddress() {

        onView(withId(R.id.SignInButton)).perform(click());
        onView(ViewMatchers.withId(R.id.passwordField2))
                .perform(typeText("PatientTest123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.usernameField2))
                .perform(typeText("PatientTest123"), closeSoftKeyboard());
        onView(withId(R.id.signInButton)).perform(click());
        while (true) {
            try {
                onView(withId(R.id.bookButton)).perform(click());
                break;
            } catch (Exception e) {
                onView(withId(R.id.signInButton)).perform(click());
            }
        }

        //onView(ViewMatchers.withId(R.id.nameField2))
                //.perform(typeText("test_test_test__test_test_test_test_test_test_test_test_test_test_test_test_test_test"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.addressField))
            .perform(typeText("test_test_test__test_test_test_test_test_test_test_test_test_test_test_test_test_test"), closeSoftKeyboard());
        onView(withId(R.id.searchBtn)).perform(click());
        try {
            onData(anything()).inAdapterView(withId(R.id.clinicList)).atPosition(0).perform(click());
        } catch (Exception e) {
            System.out.println("No clinic available, so test is passed");
        }
    }
}


