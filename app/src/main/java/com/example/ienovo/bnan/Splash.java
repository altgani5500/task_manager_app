package com.example.ienovo.bnan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // buclick();

        new android.os.Handler().postDelayed(new Runnable()


                                             {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                                                 @Override
                                                 public void run () {

//                                                      This method will be executed once the timer is over
//                                                      Start your app main activity

                                                     Intent i = new Intent(Splash.this,Login2.class);
                                                     startActivity(i);
                                                     overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                                                     // close this activity
                                                     finish();


                                                 }
                                             }

                ,SPLASH_TIME_OUT);



    }



}










