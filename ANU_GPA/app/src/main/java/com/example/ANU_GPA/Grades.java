package com.example.ANU_GPA;

/**
 * Contains Constant data about Grades.
 * @author: Kalai (u6555407) */
public enum Grades {
     HD(7),D(6),CR(5),P(4),F(0);
     int gradePoints;
    Grades(int gradePoints) {
        this.gradePoints=gradePoints;
    }
}
