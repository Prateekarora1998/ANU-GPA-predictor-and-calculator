package com.example.ANU_GPA;
import org.junit.Test;
import static org.junit.Assert.*;
/**this class is used to test basic functions of the GPA class
 * @author jared */
public class GPATest {

    @Test
    /**this test tests the base case of all values being equal to 0*/
    public void baseCase() {
        int[] testList = {0,0,0,0,0};
        GPA base = new GPA(testList);
        base.calculatePointsNeeded(0);
        assertTrue("cgpa incorrect, cgpa equals : " + base.cgpa + ", should equal 0", base.cgpa == 0);
        assertTrue("taken courses incorrect, taken courses equals : " + base.numOfTCourses + ", should equal 0", base.numOfTCourses == 0);
        }


    @Test
    /**this test check to see if it gets the correct result with basic numbers*/
    public void simpleTest() {
        int[] testList = {1,0,1,2,1};
        //the total of (7+5+(2*8) +0)/5 is 4
        float testGPA = 4;
        //the number of taken classes is 1+0+1+2+1 = 5
        int testTClasses = 5;
        GPA base = new GPA(testList);
        assertTrue("cgpa incorrect, cgpa equals : " + base.cgpa + ", should equal " + testGPA, base.cgpa == testGPA);
        assertTrue("taken courses incorrect, taken courses equals : " + base.numOfTCourses + ", should equal "+ testTClasses, base.numOfTCourses == testTClasses);
        }


    @Test
    /**this test check to see if it gets the correct result with a more complex input*/
    public void randomTest() {
        int[] testList = new int[5];
        int[] grades = {7,6,5,4,0};
        int testPoints = 0;
        int testTClasses = 0;
        float testGPA;
        for (int x = 0; x<5; x++){
            //creates test numbers between 0 and 5, inclusive
            int tempInt = (int)(Math.random()*6);
            testList[x] = tempInt;
            testPoints += grades[x]*tempInt;
            testTClasses += tempInt;
        }
        //the cgpa is points/classes
        if(testTClasses ==0){
            testGPA = 0;
        }else{
            testGPA = (float)testPoints/testTClasses;
        }
        //the number of taken classes is 1+0+1+2+1 = 5
        GPA base = new GPA(testList);
        assertTrue("cgpa incorrect, cgpa equals : " + base.cgpa + ", should equal " + testGPA, base.cgpa == testGPA);
        assertTrue("taken courses incorrect, taken courses equals : " + base.numOfTCourses + ", should equal "+ testTClasses, base.numOfTCourses == testTClasses);
    }
}