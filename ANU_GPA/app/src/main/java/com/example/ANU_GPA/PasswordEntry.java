package com.example.ANU_GPA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**this activity serves as a lock screen for the app, if you have set a password
 * @author jared */
public class PasswordEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        setContentView(R.layout.activity_password_entry);
        final String actualPassword = pass.getString("password", "");
        final Button unlockButton = findViewById(R.id.unlockButton);
        final EditText passwordGiven = findViewById(R.id.passwordEntry);



        unlockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View l){
                //check if the given password correct. if so, unlock app. else, reload activity
                if(passwordGiven.getText().toString().equals(actualPassword)){
                    Intent intent = new Intent(PasswordEntry.this, Home.class);
                    startActivity(intent);
                }else{
                    Toast failMessage = Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT);
                    failMessage.show();
                    Intent intent = new Intent(PasswordEntry.this, PasswordEntry.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }


}
