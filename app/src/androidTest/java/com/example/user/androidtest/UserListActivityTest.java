package com.example.user.androidtest;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 20/12/2018.
 */
public class UserListActivityTest {
    @Rule
    public ActivityTestRule<UserListActivity> mainActivityActivityTestRule = new ActivityTestRule<>(UserListActivity.class);

    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation()
            .addMonitor(MainActivity.class.getName(), null, false);
    public String mName ="Hello";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testMain(){
       // activity = mainActivityActivityTestRule.getActivity();
        Espresso.onView(ViewMatchers.withId(R.id.edName)).perform(ViewActions.typeText(mName));
        Espresso.onView(ViewMatchers.withId(R.id.edAddress)).perform(ViewActions.typeText(mName));
        Espresso.closeSoftKeyboard();


        Espresso.onView(ViewMatchers.withId(R.id.add)).perform(ViewActions.click());

        Activity second = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(second);



    }
    @After
    public void tearDown() throws Exception {
    }

}