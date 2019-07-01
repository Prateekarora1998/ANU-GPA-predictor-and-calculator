package com.example.ANU_GPA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**@authors:
 *  Base:Prateek Arora (u6742441)
 *  Client-Server: Kalai (u6555407)**/
public class GiveFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);
        final EditText feedbackEditText= findViewById(R.id.feedbackEditText);
        final RatingBar ratingBar= findViewById(R.id.ratingBar);
        Button submitButton =  findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ratings =(ratingBar.getRating()) +"\n";
                String feedback=feedbackEditText.getText().toString()+"\n";
                ratings=ratings.concat(feedback);
                final String overallFeedback=ratings;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        clientSender(overallFeedback.getBytes());
                    }
                };
                thread.start();
                Intent intent = new Intent(GiveFeedback.this, FeedbackThanks.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /**
     * Initiates a call to the Server and sends the the data to it.
     * @param feedbackData The feedback data which contains the rating & the textual feedback.*/
    public void clientSender(byte[] feedbackData) {
        String ip = "10.0.2.2"; // reference to development machine

        try {
            Socket socket = new Socket(ip, 5005);
            OutputStream connectedSocket = socket.getOutputStream();
            connectedSocket.write(feedbackData);
            connectedSocket.close();
            socket.close();
        }
        catch (IOException i){

        }
    }

}