package tech.mude.mude.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import tech.mude.mude.R;
import tech.mude.mude.services.AudioService;

/**
 * Created by leo on 25/03/17.
 */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        launchTestService();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent intent = new Intent(getApplicationContext(), MoodActivity.class);
                startActivity(intent);
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    // Call `launchTestService()` in the activity
    // to startup the service
    public void launchTestService() {
        // Construct our Intent specifying the Service
        Intent i = new Intent(this, AudioService.class);
        // Start the service
        startService(i);
    }
}
