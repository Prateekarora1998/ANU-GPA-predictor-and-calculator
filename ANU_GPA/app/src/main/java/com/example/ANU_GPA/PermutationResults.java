package com.example.ANU_GPA;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


/**
 * Activity which displays all of the possible permutation based on the user's input.
 * @author: Kalai (u6555407)*/
public class PermutationResults extends AppCompatActivity {

    TableLayout possibleOutputsTableLayout;
    boolean donePermutation;
    boolean doubleTap=false;
    int fetch=30; //Initial number of permutations being fetch for number of fails known
    ArrayList<AsyncTask> asyncTasks=new ArrayList<>();
    PermutationGenerator pg;
    boolean numOfFailsNeeded;
    RelativeLayout outerRelativeLayout;
    TextView noResultsTextView;
    /*Used when number of  Fails is needed.States if there isn't any possible permutation.*/
    boolean noPossiblePermutation=false;
    Toast doubleTapForMoreToast;
    Toast pleaseWaitToast;
    Toast noMorePossiblePermutationToast;
    Toast permutationFetchedToast;
    Toast stillFetchingResultsToast;
    Toast[] toasts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutation_results);

        /*Retrieving values from sharedPreference & Intents*/
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        final int nCoursesDone = sharedPreferences.getInt("numOfTCourses", 0);
        final float cgpa = sharedPreferences.getFloat("cgpa", 0);
        final int numTBTCourses = getIntent().getExtras().getInt("numOfTBTCourses");
        final float gpaWanted = getIntent().getExtras().getFloat("gpaWanted");
        numOfFailsNeeded = getIntent().getExtras().getBoolean("numOfFailsNeeded", false);

        /*Creating respective Permutation Object in accordance to the requirement */
        String[] colNames;
        ArrayList<Integer[]> permutations;
        if (numOfFailsNeeded) {
            colNames = new String[]{"HDs", "Ds", "CRs", "Ps", "Fs"};
            pg = new PermutationGenerator(cgpa, nCoursesDone, numTBTCourses + nCoursesDone, gpaWanted);
            pg.calculatePermutation();
            pg.initialise();
            permutations = pg.getPermutation();
        } else {
            colNames = new String[]{"HDs", "Ds", "CRs", "Ps"};
            Permutation p = new Permutation(cgpa, nCoursesDone, numTBTCourses + nCoursesDone, gpaWanted);
            p.calculatePermutation();
            permutations = p.getPermutation();
        }


        /*View Objects*/
        outerRelativeLayout = findViewById(R.id.outerRelativeLayout);
        possibleOutputsTableLayout = findViewById(R.id.possibleResultsTableLayout);
        TableLayout headingTableLayout = findViewById(R.id.headingTableLayout);
        ScrollView innerScrollView = findViewById(R.id.innerScrollView);
        noResultsTextView = findViewById(R.id.noResultsTextView);


        /*Initialisation & Minor configuration changes*/
        doubleTapForMoreToast = Toast.makeText(PermutationResults.this, "Double Tap For More", Toast.LENGTH_SHORT);
        pleaseWaitToast = Toast.makeText(PermutationResults.this, "Please Wait", Toast.LENGTH_LONG);
        noMorePossiblePermutationToast = Toast.makeText(PermutationResults.this, "No Possible Permutation", Toast.LENGTH_SHORT);
        permutationFetchedToast = Toast.makeText(PermutationResults.this, "Permutations Fetched! \n Double Tap For More.", Toast.LENGTH_SHORT);
        stillFetchingResultsToast = Toast.makeText(PermutationResults.this, "Still fetching permutations,Please wait!", Toast.LENGTH_LONG);
        toasts = new Toast[]{doubleTapForMoreToast, pleaseWaitToast, noMorePossiblePermutationToast, permutationFetchedToast, stillFetchingResultsToast};
        innerScrollView.setSmoothScrollingEnabled(true);


        /*Showing results*/
        if (permutations.size() > 0) {
            /*Setting the Heading Row And it's attributes*/
            TableRow headingRow = new TableRow(this);
            for (String colName : colNames) {
                TextView colTextView = new TextView(this);
                colTextView.setText(colName);
                colTextView.setTextSize(25);
                colTextView.setTextColor(Color.BLACK);
                headingRow.addView(colTextView);
            }
            headingTableLayout.addView(headingRow);
            headingTableLayout.setStretchAllColumns(true);

            /*Adding values/rows to the table*/
            TextView valueTextView;
            TableRow valueRow;
            for (Integer[] s : permutations) {
                valueRow = new TableRow(this);
                for (int val : s) {
                    valueTextView = new TextView(this);
                    valueTextView.setText(val + "\n");
                    valueTextView.setTextSize(20);
                    valueRow.addView(valueTextView);
                }
                if (numOfFailsNeeded) {
                    /*if number of fails is needed,since for number of fails = 0 situation; permutation
                    from permutation class can be used and a column of zero can be filled in the number of fail*/
                    valueTextView = new TextView(this);
                    valueTextView.setText(0 + " ");
                    valueTextView.setTextSize(20);
                    valueRow.addView(valueTextView);
                }
                possibleOutputsTableLayout.addView(valueRow);
            }
            possibleOutputsTableLayout.setStretchAllColumns(true);

        } else {
            /*noPossiblePermutation will be used for the case in which the user want the number of fails later on .*/
            noPossiblePermutation=true;
            if (!numOfFailsNeeded) {
                /*to make sure if there is no result for a situation in which user doesnt want the number of fails to be showed;
                 * Notify the user that there isn't any results*/
                noResultsTextView.setVisibility(View.VISIBLE);

                /*To make sure toast don't overlap with "Got Permutation Toast"*/
                PermutationCalc.gotPermutationToast.cancel();
                noMorePossiblePermutationToast.show();


            }

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        /*If there is no need of number of fails by the user*/
        donePermutation=true;

        if (numOfFailsNeeded) {
            donePermutation = false;
            new InfoLoader().execute(pg);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    PermutationCalc.gotPermutationToast.cancel();
                    doubleTapForMoreToast.show();
                }
            },750);

        }

        /*If only few permutation are there and the user double taps*/
        outerRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( doubleTap = !doubleTap)
                noMorePossiblePermutationToast.show();
            }
        });

        /*On double tap on the table;Fetch possible permutation*/
        possibleOutputsTableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!donePermutation && numOfFailsNeeded && (doubleTap = !doubleTap)) {
                    doubleTapForMoreToast.cancel();
                    boolean running = false;
                    /*Checking if there is an AsyncTask running */
                    for (AsyncTask asyncTask : asyncTasks) {
                        if (asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
                            permutationFetchedToast.cancel();
                            stillFetchingResultsToast.show();
                            running = true;
                            break;
                        }
                    }
                    /*Fetch results if there is'nt any AsyncTask running*/
                    if (!running) {
                        if (!donePermutation  && pg.hasNext()) {
                            pleaseWaitToast.show();
                            fetch = 150;
                            asyncTasks.add(new InfoLoader().execute(pg));
                        }
                    }
                }
                else{
                    /* Case 1: When the users does'nt want number of fails
                       Case 2: When the user wants number of fails , but there are'nt any more possible permutations*/
                        /*If the recent fetch is the last one, make sure the following toast doesn't overlap with "Permutation Found" Toast*/
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                permutationFetchedToast.cancel();
                                noMorePossiblePermutationToast.show();
                            }
                        },750);/* To slightly show that the permutations has been fetched.*/

                    }}

        });
    }


    @Override
    public void onBackPressed() {
        /* Making sure that there is'nt any AsyncTasks running when the user presses back */
        for(AsyncTask asyncTask:asyncTasks){
            asyncTask.cancel(true);
        }
        boolean cancel=true;
        for(AsyncTask asyncTask:asyncTasks){
            cancel=cancel&& asyncTask.isCancelled();
        }
        if(cancel) {
            /*Making sure toast generated within this activity stops when  this activity is destroyed.*/
            for(Toast toast:toasts){
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    /** A class which runs on a separate thread in parallel to the UI thread*/
    public class InfoLoader extends  AsyncTask<PermutationGenerator,String,ArrayList<Integer[]>>{
        @Override
        protected ArrayList<Integer[]> doInBackground(PermutationGenerator... permutationGenerators) {
            ArrayList<Integer[]> output=new ArrayList<>();
            PermutationGenerator pg=permutationGenerators[0];
            Integer[] permutation;
            int n=0;
            while(n<fetch&& pg.hasNext()){
                n++;
                if(!isCancelled()&&(permutation=pg.next())!=null){
                    output.add(permutation);
                }else{
                    donePermutation=true;
                    break;
                }
            }
            return output;
        }


        /**
         * Once background activity is done display the respective results*/
        @Override
        protected void onPostExecute( ArrayList<Integer[]> result) {
            int countFetch=0;
            for (Integer[] permutation : result) {
                countFetch++;
                TableRow valueRow2 = new TableRow(PermutationResults.this);
                for (int i = 1; i < 5; i++) {
                    TextView valueTextView = new TextView(PermutationResults.this);
                    valueTextView.setText(permutation[i] + "\n");
                    valueTextView.setTextSize(20);
                    valueRow2.addView(valueTextView);
                }
                TextView valueTextView = new TextView(PermutationResults.this);
                valueTextView.setText(permutation[0] + "\n");
                valueTextView.setTextSize(20);
                valueRow2.addView(valueTextView);
                possibleOutputsTableLayout.addView(valueRow2);

            }
            possibleOutputsTableLayout.setStretchAllColumns(true);
            if(noPossiblePermutation && countFetch==0 && fetch==30){
                /*When user needs the number of fails in the results and there are no results from
                 both the permutations: permutation:getPermutation & PermutationGenerator:next()
                 notify the user that there are'nt any results*/
                noResultsTextView.setVisibility(View.VISIBLE);
            }
            if(fetch>31) {
                stillFetchingResultsToast.cancel();
                permutationFetchedToast.show();
            }
        }
    }
}