package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

/**
 * Activity for calculating possible permutation based on the user's inputs.
 * @authors: Kalai(u6555407)
 * The code for knowing the screen size is from:
 * https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android*/
public class PermutationCalc extends AppCompatActivity {

    boolean mEntryButtonClicked = false;
    int visibility = View.VISIBLE;
    static Toast gotPermutationToast;

    /**
     * Based on given key of SharedPreference with the respective sharedPreference.
     * the value stored can be retrieved.
     * @param key (sharedPreference based) String Key on which data is to be retrieved as it'srespective type.
     * @return The respective value of the key as an String.
     * */
    public <T> T sPreferenceRetriever(String key,SharedPreferences sp) {
        Map<String, ?> data = sp.getAll();
        if(data.containsKey(key)){
            T genericValue=(T)data.get(key);
            if (genericValue.getClass() == Integer.class) {
                return (T)Integer.valueOf(sp.getInt(key, 0))  ;
            }
            if (genericValue.getClass() == Float.class) {
                return (T)Float.valueOf(sp.getFloat(key, 0)) ;
            }
            if (genericValue.getClass() == String.class) {
                return (T)sp.getString(key, "");
            }}
        return (T)"";
    }


    /**
     * Provides a string representation of the given sharedPreference based on the given attributes.
     * @param sp The respective SharedPreference Object.
     * @param attributes An Array of attributes/Keys of sp
     * @return  A String representation of the sharedPreference object.*/
    public String localDataStatus(String[] displayAttributes,String[] attributes,SharedPreferences sp){
        String input="Locally Stored Data :  \n";
        for(int i=0;i<attributes.length;i++)
        {
            input+= displayAttributes[i]+": " +sPreferenceRetriever(attributes[i],sp) + " \n";
        }
        return input;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutation_calc);

        /*View Objects*/
        final TextView cgpaTextView =findViewById(R.id.cgpaTextView);
        final EditText cgpaEditText= findViewById(R.id.cgpaEditText);
        final EditText numOfCourseTBTEditText=findViewById(R.id.numOfTBTCourseEditText);
        final EditText gpaWantedEditText= findViewById(R.id.sNumberOfCoursesEditText);
        final TextView numOfCourseDoneTextView =  findViewById(R.id.numOfCourseDoneTextView);
        final EditText numOfCourseDoneEditText= findViewById(R.id.numOfCourseDoneEditText);
        final Button manualEntryButton = findViewById(R.id.manualEntryButton);
        final Button reCalculateButton = findViewById(R.id.reCalculateButton);
        final Button submitButton = findViewById(R.id.submitButton);
        final ScrollView scrollView = findViewById(R.id.scrollView);
        final TextView localDataTextView =findViewById(R.id.localDataTextView);
        final Switch numOfFailsNeededSwitch=findViewById(R.id.numOfFailsNeededSwitch);

        /*Initialisation*/
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        localDataTextView.setText(localDataStatus(new String[]{"CGPA","Number of taken courses"},
                new String[]{"cgpa","numOfTCourses"},sharedPreferences));

        /*Getting information about screen*/
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int screenOrientation = mWindowManager.getDefaultDisplay().getRotation();
        final int screenWidth=displayMetrics.widthPixels;
        final int screenHeight=displayMetrics.heightPixels;


        manualEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To prevent user interaction when animating
                manualEntryButton.setEnabled(false);
                mEntryButtonClicked=!mEntryButtonClicked;

                //Default:mEntryButton not clicked
                float mEntryButtonTranslation = (screenOrientation== Surface.ROTATION_0)?
                        -(screenWidth/3.5f): -(screenWidth/2.65f);
                System.out.println("Screen HEIGHT"+screenWidth);
                float submitButtonTranslation=(screenOrientation== Surface.ROTATION_0)?
                        -400- ((screenHeight-1800)/20f):-(screenWidth/4.5f);
                float scaleFactor=0.15f;
                int imegpaWanted=EditorInfo.IME_ACTION_DONE;
                visibility=View.INVISIBLE;//For toggling scaleEffect.
                int alpha=1;
                int scroll=0;

                if(mEntryButtonClicked){
                    imegpaWanted=EditorInfo.IME_ACTION_NEXT;
                    mEntryButtonTranslation=-mEntryButtonTranslation;
                    submitButtonTranslation=-submitButtonTranslation;
                    visibility=View.VISIBLE;
                    scaleFactor=-scaleFactor;
                    alpha=-alpha;
                    scroll=screenHeight;
                    // to make sure users has an overview of everything
                    gpaWantedEditText.setEnabled(false);
                    numOfCourseTBTEditText.setEnabled(false);
                    Toast.makeText(PermutationCalc.this, "Click Manual Entry Again to Undo", Toast.LENGTH_LONG).show();

                }
                gpaWantedEditText.setEnabled(true);
                numOfCourseTBTEditText.setEnabled(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numOfCourseTBTEditText.requestFocus();
                    }
                }, 100);

                gpaWantedEditText.setImeOptions(imegpaWanted);
                manualEntryButton.animate().scaleXBy(scaleFactor).scaleYBy(scaleFactor).setDuration(600);
                submitButton.animate().scaleXBy(scaleFactor).scaleYBy(scaleFactor).setDuration(600);
                submitButton.animate().translationYBy(submitButtonTranslation).setDuration(600);
                manualEntryButton.animate().translationXBy(mEntryButtonTranslation).setDuration(600);

                //reCalculateButton will disappear when the user knows his/her cgpa.
                reCalculateButton.animate().alpha(alpha).setDuration(750);

                //enable user interacting when animation is most probably done.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        manualEntryButton.setEnabled(true);
                    }
                }, 650);

                reCalculateButton.setEnabled(!mEntryButtonClicked);
                numOfCourseDoneTextView.setVisibility(visibility);
                numOfCourseDoneEditText.setVisibility(visibility);
                cgpaTextView.setVisibility(visibility);
                cgpaEditText.setVisibility(visibility);
                scrollView.smoothScrollTo(0,scroll);
            }
        });


        reCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermutationCalc.this, GPACalc.class);
                startActivity(intent);
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int numOfTCourses=0;
              float cgpa=0;
              int numOfTBTCourses=0;
              float gpaWanted=0;
                boolean errorFree=true;
                try{
                    if(mEntryButtonClicked) {//If Known button has been clicked.
                       numOfTCourses=Integer.parseInt(((EditText)findViewById(R.id.numOfCourseDoneEditText))
                                .getText().toString());
                         cgpa=Float.parseFloat(((EditText) findViewById(R.id.cgpaEditText))
                                .getText().toString());
                        editor.putInt("numOfTCourses", numOfTCourses);
                        editor.putFloat("cgpa",cgpa );
                        editor.apply();
                    }
                     numOfTBTCourses = Integer.parseInt(((EditText) findViewById(R.id.numOfTBTCourseEditText))
                                            .getText().toString());
                     gpaWanted = Float.parseFloat(((EditText) findViewById(R.id.sNumberOfCoursesEditText))
                                     .getText().toString());

                     //Preventing user from giving unreasonable inputs
                    if(numOfTBTCourses>80|gpaWanted>7|cgpa>7|numOfTCourses>80){
                        throw new NumberFormatException();
                    }
                }catch(NumberFormatException n){
                    errorFree=false;
                    Toast.makeText(PermutationCalc.this,"Wrong input ",Toast.LENGTH_SHORT).show();
                }
                if(errorFree){
                    gotPermutationToast=Toast.makeText(PermutationCalc.this,"Got the Permutations",Toast.LENGTH_SHORT);
                    gotPermutationToast.show();
                    Intent intent = new Intent(PermutationCalc.this,PermutationResults.class);
                    intent.putExtra("numOfTBTCourses",numOfTBTCourses);
                    intent.putExtra("gpaWanted",gpaWanted);
                    intent.putExtra("numOfFailsNeeded",numOfFailsNeededSwitch.isChecked());
                    startActivity(intent);}
            }
        });


    }

    /*Once an Inner activity is closed & given that this is the parent activity of the inner activity
    Update the display for the LocalData*/
    protected void onResume() {
        super.onResume();
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        final TextView localDataTextView =findViewById(R.id.localDataTextView);
        localDataTextView.setText(localDataStatus(new String[]{"CGPA","Number of taken courses"},
                new String[]{"cgpa","numOfTCourses"},sharedPreferences));
    }

}