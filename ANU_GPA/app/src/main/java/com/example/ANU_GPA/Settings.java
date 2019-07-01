package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;


/**@authors:
Everything else: Prateek Arora (u6742441)
animationSwitch: Kalai(u6555407)
 */

public class Settings extends AppCompatActivity {
    ScaleEffect scaleEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /*View Objects*/
        final Switch animationSwitch=findViewById(R.id.animationSwitch);
        final SharedPreferences dataSharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        LinearLayout passwordLinearLayout = findViewById(R.id.gpaCalcLinearLayout);
        LinearLayout feedbackLinearLayout = findViewById(R.id.permutationLinearLayout);
        LinearLayout updateLinearLayout = findViewById(R.id.settingsLinearLayout);
        LinearLayout aboutLinearLayout = findViewById(R.id.aboutLinearLayout);
        LinearLayout animationLinearLayout=findViewById(R.id.animationLinearLayout);

        /*Initialisation*/
        final LinearLayout[] layouts=new LinearLayout[]
                {passwordLinearLayout,feedbackLinearLayout
                        ,animationLinearLayout,updateLinearLayout,aboutLinearLayout};
        scaleEffect =new ScaleEffect(layouts);
        if(dataSharedPreferences.getBoolean("animation",true)){
            animationSwitch.setChecked(true);
            scaleEffect.startAnimation();
        }
        else{
            scaleEffect.setAnimationEnd(true);
            animationSwitch.setChecked(false);}

        passwordLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //depending on if you have a password, either sends you to set or change password
                final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
                if(pass.getString("password", "").equals("")){
                    Intent intent = new Intent(Settings.this,PasswordSet.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Settings.this,Password.class);
                    startActivity(intent);
                }

            }
        });

        feedbackLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, GiveFeedback.class);
                startActivity(intent);

            }
        });

        updateLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //blocks access to update if it will be useless(no data to update)
                final SharedPreferences data = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
                if(data.getBoolean("hasValues", false)){
                    Intent intent = new Intent(Settings.this, Update.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Settings.this, "There is no data to update", Toast.LENGTH_LONG).show();
                }
            }
        });

        aboutLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,About.class);
                startActivity(intent);
            }
        });


        animationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                animationSwitch.setEnabled(false);
                Toast.makeText(Settings.this, "Please wait.", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = dataSharedPreferences.edit();
                if(isChecked){
                    editor.putBoolean("animation",true);
                    scaleEffect.setAnimationEnd(false);
                    scaleEffect.startAnimation();
                }
                else{
                    editor.putBoolean("animation",false);
                    scaleEffect.setAnimationEnd(true);
                }
                editor.apply();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationSwitch.setEnabled(true);
                    }
                },  5000);
            }
        });

    }
}
