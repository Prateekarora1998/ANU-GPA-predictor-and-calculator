package com.example.ANU_GPA;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**this class allows you to update the stored data of the GPA calculator.
 * rather than supplying all your grades, the user may supply only the new grades since they last calculated their gpa
 * @authors:
 * Jared:Logic behind Updating cgpa
 * Kalai:Code structure & retrieval of data (from GPACalc) */
public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        /*View Objects*/
        Button doneButton = findViewById(R.id.doneButton);
        final EditText hdEditText = findViewById(R.id.hdEditText);
        final EditText dEditText = findViewById(R.id.dEditText);
        final EditText cEditText = findViewById(R.id.cEditText);
        final EditText pEditText = findViewById(R.id.pEditText);
        final EditText fEditText = findViewById(R.id.fEditText);
        final TextView resultTextView = findViewById(R.id.uResultTextView);
        final EditText[] numbersOfGrades = new EditText[]{hdEditText, dEditText, cEditText, pEditText, fEditText};

        /*Initialisations*/
        final SharedPreferences data = getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        final Grades[] GradeValues = Grades.values();
        resultTextView.setText(data.getFloat("cgpa", 0) +"");
        ScaleEffect scaleEffect= new ScaleEffect(resultTextView);
        scaleEffect.startAnimation();
        scaleEffect.setDuration(250);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                int totalPoints = data.getInt("currentPoints", 0);
                int totalClasses = data.getInt("numOfTCourses", 0);
                for (int i = 0; i < 5; i++) {
                    try {
                        totalPoints = GradeValues[i].gradePoints * Integer.parseInt(numbersOfGrades[i].getText().toString()) + totalPoints;
                        totalClasses = totalClasses + Integer.parseInt(numbersOfGrades[i].getText().toString());
                    } catch (NumberFormatException n) {
                        error = true;
                    }
                }
                if (error) {
                    Toast.makeText(Update.this, "Note:Your are neglecting some attributes;" +
                            "It's values will be considered as 0.", Toast.LENGTH_SHORT).show();

                    }

                /**@author: Jared*/
                SharedPreferences.Editor edit = data.edit();
                edit.putInt("numOfTCourses", totalClasses);
                edit.putInt("currentPoints", totalPoints);
                if(totalClasses == 0){
                    edit.putFloat("cgpa", (float)0.0);
                }else{
                    edit.putFloat("cgpa", ((float)totalPoints / totalClasses));
                }
                edit.commit();
                resultTextView.setText(data.getFloat("cgpa", 0) +"");
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  Toast.makeText(Update.this,"CGPA updated locally.",Toast.LENGTH_LONG).show();
                                              }
                                          },2500);

            }
        });
    }
}
