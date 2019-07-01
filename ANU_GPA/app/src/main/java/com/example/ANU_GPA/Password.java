package com.example.ANU_GPA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**a class to deal with the changing of an existing password
 * @authors: jared
 *        prateek - removePasswordButton functionality*/

public class Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


        /*View Objects*/
        final Button changePasswordButton= findViewById(R.id.changePasswordButton);
        final EditText oldPassEditText = findViewById(R.id.oldPassEditText);
        final EditText newPassEditText = findViewById(R.id.newPassEditText);
        final EditText confirmNewPassEditText = findViewById(R.id.confirmNewPassEditText);
        final Button removePasswordButton= findViewById(R.id.removePasswordButton);

        /*Initialisation*/
        final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        final String actualPass = pass.getString("password", "");



        changePasswordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View l){
                //check if old password is correct. if not, reload activity
                if(oldPassEditText.getText().toString().equals(actualPass)){

                    if(newPassEditText.getText().toString().equals("")){
                        Toast.makeText(Password.this,"Please enter a new password",Toast.LENGTH_SHORT).show();
                    }
                    //check if new passwords match. if not, reload activity
                    else if(newPassEditText.getText().toString().equals(confirmNewPassEditText.getText().toString())){
                        SharedPreferences.Editor edit = pass.edit();
                        edit.putString("password", newPassEditText.getText().toString());
                        edit.commit();
                        Toast.makeText(Password.this,"Password is changed.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast failMessage = Toast.makeText(getApplicationContext(), "new passwords do not match", Toast.LENGTH_SHORT);
                        failMessage.show();
                        Intent intent = new Intent(Password.this, Password.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast failMessage = Toast.makeText(getApplicationContext(), "old password is not correct", Toast.LENGTH_SHORT);
                    failMessage.show();
                    Intent intent = new Intent(Password.this, Password.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

        removePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if old password is correct. if not, reload activity
                if(!oldPassEditText.getText().toString().equals(actualPass)){
                    Toast.makeText(Password.this," Please enter your old password",Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences.Editor edit = pass.edit();
                    edit.putString("password", "");
                    edit.apply();
                    Toast.makeText(Password.this," Password removed",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}