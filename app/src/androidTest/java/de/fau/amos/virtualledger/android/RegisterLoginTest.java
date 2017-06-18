package de.fau.amos.virtualledger.android;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.authentication.demo.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterLoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void registerAndLogin() throws InterruptedException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
        final String email = "test@user" + sdf.format(new Date()) + ".com";
        onView(withId(R.id.textViewLogin_RegisterFirst)).perform(click());
        onView(withId(R.id.RegistrationView)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.terms_and_conditions)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.FirstName)).perform(typeText("Test")).perform(closeSoftKeyboard());
        onView(withId(R.id.LastName)).perform(typeText("User")).perform(closeSoftKeyboard());
        onView(withId(R.id.Email)).perform(typeText(email)).perform(closeSoftKeyboard());
        onView(withId(R.id.Password)).perform(typeText("testpassword")).perform(closeSoftKeyboard());
        onView(withId(R.id.button_register)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.userIDField)).perform(typeText(email)).perform(closeSoftKeyboard());
        onView(withId(R.id.SecretField)).perform(typeText("testpassword")).perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.banking_overview_no_bank_access_text_view)).check(matches(isCompletelyDisplayed()));
    }
}
