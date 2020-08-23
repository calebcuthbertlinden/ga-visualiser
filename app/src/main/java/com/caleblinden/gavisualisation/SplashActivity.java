package com.caleblinden.gavisualisation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPref";
    private static final String SEEN_ONBOARDING = "SeenOnboarding";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showSplash();
    }

    private void showSplash() {
        final Handler handler = new Handler();
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        navigateToStartingActivity();
                    }
                });
            }
        };

        Timer timer = new Timer();
        timer.schedule(myTimerTask, 5000);
    }

    private void navigateToStartingActivity() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean seenOnboarding = prefs.getBoolean(SEEN_ONBOARDING, false);

        if (seenOnboarding) {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            startActivity(new Intent(SplashActivity.this, ParamterInputActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, ParamterInputActivity.class));
        }
    }
}
