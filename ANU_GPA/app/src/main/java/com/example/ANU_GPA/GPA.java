package com.example.ANU_GPA;

/**
 * A class in which upon retrieval of data it does any calculation of GPA or recalculation if necessary.
 * @author jared
 **/
public class GPA {

    //All instance variable below are required for pointsNeeded
    int numOfCourses;//total num of courses
    int numOfTCourses;//num of courses Taken already
    float cgpa;//Current GPA
    int currentPoints;//current number of grade points
    //the below variables are related to the calculate total points function
    float gpaWanted;
    int pointsNeeded;
    final Grades[] grades=Grades.values();


    /**primary constructor
     * @param grades - a list of the users grades, in order: HD, D, C, P, F*/
    public GPA(int[] grades) {
        gpaCalc(grades);
    }


    /**a secondary constructor
     * @param gpa - the users current gpa
     * @param coursesDone - the number of uni coursed completed by the user
     * @param totalCourses - the total number of courses required to finish the users program/degree*/
    public GPA(float gpa, int coursesDone, int totalCourses) {
        cgpa = gpa;
        numOfTCourses = coursesDone;
        numOfCourses = totalCourses;
        currentPoints = (int)(cgpa*numOfTCourses+0.5);
    }


    /**calculates cgpa, numOfTCourses and currentPoints from a list of ints
     * @param data - a list of the number of each grade achieved, in order: HD, D, C, P, F*/
    public void gpaCalc(int[] data) {
        currentPoints = 0;
        numOfTCourses = 0;
        for (int x = 0; x < 5; x++) {
            currentPoints += (data[x]*grades[x].gradePoints);
            numOfTCourses += data[x];
        }
        cgpa = (numOfTCourses!=0)?(float)currentPoints/numOfTCourses:0;//for not producing a NaN
    }

    
    /**Calculates PointsNeeded based on numOfTCourses & cgpaJared
     * @param wantedGPA - the gpa you wish to reach from your current gpa*/
    public void calculatePointsNeeded(float wantedGPA){
        pointsNeeded = (int)(wantedGPA*(numOfCourses)+0.5)-currentPoints;
        gpaWanted = wantedGPA;
    }
}
