package com.example.ANU_GPA;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**@author  Prateek Arora (u6742441)*/
public class FeedbackThanks extends AppCompatActivity {
    private static int timeOut = 3300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_thanks);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, timeOut); /** Halts for 3.3 seconds and then goes to the previous screen*/

    }
}
