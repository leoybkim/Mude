package tech.mude.mude.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        // Custom action bar font
        TextView actionBar = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        actionBar.setLayoutParams(lp);
        actionBar.setText("Mude");
        actionBar.setTextSize(24);
        actionBar.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = Typeface.createFromAsset(getAssets(), "Exo-Regular.otf");
        actionBar.setTypeface(tf);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(actionBar);

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
