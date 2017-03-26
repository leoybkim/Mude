package tech.mude.mude.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tech.mude.mude.R;
import tech.mude.mude.services.AudioService;

/**
 * Created by leo on 25/03/17.
 */

public class MoodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        TextView tvHowAreYou = (TextView) findViewById(R.id.how_are_you);
        tvHowAreYou.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MLActivity.class);
                startActivity(intent);
            }
        });
        // Launch service
        launchTestService();
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

