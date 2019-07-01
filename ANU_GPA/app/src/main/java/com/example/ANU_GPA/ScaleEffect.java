package com.example.ANU_GPA;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;

import java.util.HashMap;


/**
 * A class which does a zoom in zoom out effect on any View objects.
 * @author: Kalai (u6555407)*/

public  class ScaleEffect  <T extends View>{

    private int duration=2000;
    T[] viewObjects;
    T element;
    static boolean animationEnd;
    boolean singleViewObject;

    void initialise(){
        animationEnd=false;
    }

    /*Constructor for multiple View objects*/
    ScaleEffect(T [] viewObjects){
        singleViewObject=false;
        this.viewObjects =viewObjects;
    }

    /*Constructor for a single View object(For extensibility)*/
    ScaleEffect(T element){
        singleViewObject=true;
       this.element=element;
    }

    /*Starts the animation for a group of View objects with a given latency form each object*/
    void startAnimation() {
        if (singleViewObject) {
            runEffect(element);
        } else {
            for (int i = 0; i < viewObjects.length; i++) {
                final T val = viewObjects[i];
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runEffect(val);
                    }
                }, (i) * 1000);
            }
        }
    }

    /*Setter method for ending the animation indirectly.*/
    public void setAnimationEnd(boolean animationEnd) {
        this.animationEnd = animationEnd;
    }

    /**Initiates an animation for the input View object.
     *@param val The view object  */
    void runEffect(T val){
        final AnimatorSet effect =scaleEffect(val);
        effect.start();
        AnimatorListenerAdapter adapter = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!animationEnd) {
                    effect.start();
                }
            }
        };
        effect.addListener(adapter);
    }


    /**
     * Combines property aniations into sets and apply it to the View object*/
    public AnimatorSet scaleEffect(T viewObject){
        AnimatorSet scaleIn =new AnimatorSet();
        AnimatorSet scaleOut=new AnimatorSet();
        final AnimatorSet effect=new AnimatorSet();
        ObjectAnimator scaleX= ObjectAnimator.ofFloat(viewObject, "scaleX", 0.95f);
        ObjectAnimator scaleY= ObjectAnimator.ofFloat(viewObject, "scaleY", 0.95f);
        ObjectAnimator originalScaleX= ObjectAnimator.ofFloat(viewObject, "scaleX", 1);
        ObjectAnimator originalScaleY= ObjectAnimator.ofFloat(viewObject, "scaleY", 1);
        scaleIn.setDuration(duration);
        scaleOut.setDuration(duration);
        scaleIn.playTogether(scaleX,scaleY);
        scaleOut.playTogether(originalScaleX,originalScaleY);
        effect.playSequentially(scaleIn,scaleOut);
        return effect;
    }

    void setDuration(int duration){
        this.duration=duration;
    }


}
