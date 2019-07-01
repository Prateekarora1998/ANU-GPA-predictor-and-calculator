package com.example.ANU_GPA;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
/**a class used to perform integration testing on the app
 * @author jared*/
public class MainActivityTest {
    int[] resources=new int[]
                   {R.id.hdEditText,R.id.dEditText,R.id.cEditText,R.id.pEditText,R.id.fEditText};

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    /**the first test, testing if it works when values are 0*/
    public void baseCase(){
        String[] testArray = {"0","0","0","0","0","0","0","0","0","0"};
        mainActivityTestCode(testArray);
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences dataTest = context.getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        //all values are 0
        assertTrue("cgpa incorrect, cgpa equals : " + dataTest.getFloat("cgpa", -1) + ", should equal 0", dataTest.getFloat("cgpa", -1) == 0);
        assertTrue("currentPoints incorrect, currentPoints equals : " + dataTest.getInt("currentPoints", -1) + ", should equal 0", dataTest.getInt("currentPoints", -1) == 0);
        assertTrue("courses incorrect, courses equals : " + dataTest.getInt("numOfTCourses", -1) + ", should equal 0", dataTest.getInt("numOfTCourses", -1) == 0);
    }
    @Test
    /**the second test, doing a basic check*/
    public void test1(){
        String[] testArray = {"1","1","1","1","1","1","1","1","1","1"};
        mainActivityTestCode(testArray);
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences dataTest = context.getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        //class number is 10, points from grades is 44 and cgpa is 4.4
        assertTrue("cgpa incorrect, cgpa equals : " + dataTest.getFloat("cgpa", -1) + ", should equal 4.4", dataTest.getFloat("cgpa", -1) == (float)4.4);
        assertTrue("currentPoints incorrect, currentPoints equals : " + dataTest.getInt("currentPoints", -1) + ", should equal 44", dataTest.getInt("currentPoints", -1) == 44);
        assertTrue("courses incorrect, courses equals : " + dataTest.getInt("numOfTCourses", -1) + ", should equal 10", dataTest.getInt("numOfTCourses", -1) == 10);
    }
    @Test
    /**the third test, where each input is a random number between 1 and 3*/
    public void randTest(){
        String[] testArray = new String[10];
        int[] intArray = new int[10];
        for(int i=0;i<10;i++){
            int temp = (int)(Math.random()*3+1);
            testArray[i] = Integer.toString(temp);
            intArray[i] = temp;
        }
        int currentPoints = 0;
        int classNumber = 0;
        float cgpa;
        int [] gradeValues = {7,6,5,4,0};
        for(int i = 0; i <5; i++){
            currentPoints = currentPoints+(intArray[i]+ intArray[i+5])*gradeValues[i];
            classNumber = classNumber + intArray[i]+ intArray[i+5];
        }
        cgpa = (float) currentPoints/classNumber;
        mainActivityTestCode(testArray);
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences dataTest = context.getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        // cgpa, classNumber and currentPoints are test values
        assertTrue("cgpa incorrect, cgpa equals : " + dataTest.getFloat("cgpa", -1) + ", should equal "+ cgpa, dataTest.getFloat("cgpa", -1) == cgpa);
        assertTrue("currentPoints incorrect, currentPoints equals : " + dataTest.getInt("currentPoints", -1) + ", should equal "+ currentPoints, dataTest.getInt("currentPoints", -1) == currentPoints);
        assertTrue("courses incorrect, courses equals : " + dataTest.getInt("numOfTCourses", -1) + ", should equal " + classNumber, dataTest.getInt("numOfTCourses", -1) == classNumber);
    }

    //code below this line is reformatted, but automatically generated using espresso

    /** method to cut down of repetition in mainActivityTestCode, inner code from espresso
     * @param input - the string being entered into the editText
     * @param position - the position of the editText being filled
     * @param id - the id of the editText being filled*/
    public void enterData(String input,int position,int id){
        onView(
                allOf(withId(id),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                position))).perform(scrollTo(), replaceText(input), closeSoftKeyboard());


    }
    /** method to cut down of repetition in mainActivityTestCode, inner code mostly from espresso
     * @param position - the position of the object being clicked within the activity
     * @param id - the id of the object being clicked
     * @param layout - a bool to swap the code used to ourClick, if it is a button or layout*/
    public void ourClick(int position, int id, boolean layout) {
        if (layout) {
            ViewInteraction linearLayout = onView(
                    allOf(withId(id),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.widget.RelativeLayout")),
                                            0),
                                    position),
                            isDisplayed()));
            linearLayout.perform(ViewActions.click());
        }else{
            ViewInteraction appCompatButton = onView(
                    allOf(withId(id), withText("Done"),
                            childAtPosition(
                                    childAtPosition(
                                            withId(R.id.scrollView),
                                            0),
                                    position)));
            appCompatButton.perform(scrollTo(), ViewActions.click());
        }
    }

    /**runs through the app, entering gpa and update, typing in values and generating results.
     * autogenerated using espresso, then formatted
     * @param stringList - an array of strings, which are the values input into each editText*/
    public void mainActivityTestCode(String[] stringList) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences passwords = context.getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        //made the following if statement to account for passwords. the code within is my own(jared graf)
        if(passwords.getString("password", "")!=""){
            ViewInteraction passEntry = onView(allOf(withId(R.id.passwordEntry), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 1)));
            passEntry.perform(replaceText(passwords.getString("password", "")), closeSoftKeyboard());
            ViewInteraction enterButton = onView(allOf(withId(R.id.doneButton), withText("unlock"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 2)));
            enterButton.perform(click());
        }
        ourClick(1, R.id.gpaCalcLinearLayout, true);
        for(int i=0;i<5;i++){
            enterData(stringList[i],2*i+1,resources[i]);
        }
        ourClick(10,R.id.doneButton, false );
        pressBack();
        ourClick(3, R.id.settingsLinearLayout, true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ourClick(3, R.id.settingsLinearLayout, true);
        for(int i=0;i<5;i++){
            enterData(stringList[i+5],2*i+1,resources[i]);
        }
        ourClick(10, R.id.doneButton, false);
        pressBack();
        ourClick(1, R.id.gpaCalcLinearLayout, true);
    }
    /**autogenerated using espresso*/
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
