package com.example.ANU_GPA;

import java.util.Stack;

/**A class which give permutations based on the input attributes of the user in a python-yield style.
 * @author Kalai (u6555407) */
public class PermutationGenerator extends Permutation {

     /*trackers will act like a storage which tracks the number of grades whose
     value will be used in accordance to the sequence of calculating a permutation
     Fs,HDs,Ds,CRs*/
    static int[] trackers;
    static Stack<Integer> stack ;
    Grades[] grades=Grades.values();
    static int sumOfStack;

    /*Initialise the value only once, to avoid initialising values unnecessarily*/
    void initialise(){
        trackers=new int[]{1,0,0,0};
        sumOfStack=0;
        stack=new Stack<>();
    }

    /**Primary constructor*/
    PermutationGenerator(float cgpa, int coursesDone, int totalCourses,float gpaWanted){
        super(cgpa,  coursesDone, totalCourses, gpaWanted);
    }

   /**
    * When called it will produce the next possible permutation if it is available.
    * @return a permutation based on  the class variables mentioned above*/
    Integer[] next(){
        int size;
        int val;
        boolean condition;
        int numOfFails;
        while(hasNext()){
                if (stack.size() == 4){
                    numOfFails=stack.get(0);
                    val = numOfTBTCourses+(2*numOfFails) - sumOfStack;
                    stack.push(val);
                    int calcTCourses=numOfFails+stack.get(1)+stack.get(2)
                                     +stack.get(3)+stack.get(4);
                        if (val>=0&&calcTCourses-numOfFails==numOfTBTCourses+numOfFails
                                &&calculateGPA(calcTCourses)==gpaWanted) {
                           Integer[] output= (stack.toArray(new Integer[5]));
                        stack.pop();
                        sumOfStack-=stack.pop();
                        return output; }
                        stack.pop();//removing the  5th element(pass)
                    sumOfStack -= stack.pop();// updating the sumOfStack
                } else {
                    size = stack.size();
                    val = trackers[size];
                   condition= (size==0)?trackers[0]<numOfTBTCourses+3:sumOfStack + val < numOfTBTCourses+(trackers[0]-1)*2+1;
                    if (condition) {
                        sumOfStack += val;
                        ++trackers[size];
                        stack.push(val);
                    } else {
                        //If tracker's value has reached it's max
                        trackers[size] = 0;
                        val = stack.pop();
                        sumOfStack -= val;
                    }
                }
        }
    return null;
    }

    /**
     * Calculates the gpa based on the class variables and current number of totalCourses counted in next().
     * @param calcTCourses the total number of courses being calculated at a certain point of execution in next()*/
    float calculateGPA(int calcTCourses){
        float gpa=0;
        //Calculating Points
        for(int i=0;i<4;i++){
            gpa+=stack.get(i+1)*grades[i].gradePoints;
        }
        return ((gpa/calcTCourses)*100)/100;
    }

    /**Identifies whether there exist another possible permutation */
    boolean hasNext(){
        return super.validData && trackers[0]<numOfTBTCourses+3;
    }

}
