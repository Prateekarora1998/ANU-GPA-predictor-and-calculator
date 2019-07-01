package com.example.ANU_GPA;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**this activity serves as a way to set your password, if you have no password
 * @author jared */
public class PasswordSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        setContentView(R.layout.activity_password_set);
        final Button setButton = findViewById(R.id.setButton);
        final EditText passwordChosen = findViewById(R.id.passwordEntry);


        setButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View l){
                String temp = passwordChosen.getText().toString();
                SharedPreferences.Editor edit = pass.edit();
                edit.putString("password", temp);
                edit.commit();
                finish();
            }
        });
        }
}
