package com.example.ANU_GPA;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

/**
 *An activity which calculates the GPA.
 * @author: Kalai(u6555407)*/
public class GPACalc extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calc);

        /*View Objects*/
        final TextView resultTextView = findViewById(R.id.resultTextView);
        Button doneButton = findViewById(R.id.doneButton);
        final EditText hdEditText = findViewById(R.id.hdEditText);
        final EditText dEditText =  findViewById(R.id.dEditText);
        final EditText cEditText =  findViewById(R.id.cEditText);
        final EditText pEditText =  findViewById(R.id.pEditText);
        final EditText fEditText =  findViewById(R.id.fEditText);
        final ScrollView scrollView = findViewById(R.id.scrollView);

        /*Initialisation*/
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("hasValues", false)){
            findViewById(R.id.yourGpaIsTextView).setVisibility(View.VISIBLE);
            resultTextView.setText(sharedPreferences.getFloat("cgpa", 0) +"");
            resultTextView.setVisibility(View.VISIBLE);
            ScaleEffect scaleEffect= new ScaleEffect(resultTextView);
            scaleEffect.startAnimation();
            scaleEffect.setDuration(250);
        }


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                EditText[] numbersOfGrades = new EditText[]{hdEditText, dEditText, cEditText, pEditText, fEditText};
                int[] nGrades = new int[5];
                boolean error=false;
                for (int i = 0; i < 5; i++) {
                    try {
                        nGrades[i] = Integer.parseInt(numbersOfGrades[i].getText().toString());
                    } catch (NumberFormatException n) {
                        error=true;
                        nGrades[i]=0;
                    }
                }
                if(error)
                {Toast.makeText(GPACalc.this, "Note:Your are neglecting some attributes;" +
                        "It's values will be considered as 0.", Toast.LENGTH_SHORT).show();}

                GPA gpa = new GPA(nGrades);
                findViewById(R.id.yourGpaIsTextView).setVisibility(View.VISIBLE);
                resultTextView.setText(gpa.cgpa +"");
                editor.putFloat("cgpa", gpa.cgpa);
                editor.putString("grades", Arrays.toString(nGrades));
                editor.putInt("numOfTCourses", gpa.numOfTCourses);
                editor.putInt("currentPoints", gpa.currentPoints);
                editor.putBoolean("hasValues", true);
                editor.apply();
                resultTextView.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GPACalc.this, "Extracted grades.", Toast.LENGTH_LONG).show();
                    }
                },2250);
                scrollView.fullScroll(View.FOCUS_UP);
            }
        });
    }
}