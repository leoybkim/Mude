package tech.mude.mude.activities;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

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

        // Launch service
        AudioService.stopService();
        launchTestService();
        ImageButton annoyed = (ImageButton) findViewById(R.id.annoyed);
        annoyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(7);
                newFragment.show(ft,"Dialog");

            }
        });
        ImageButton angry = (ImageButton) findViewById(R.id.angry);
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(9);
                newFragment.show(ft,"Dialog");
            }
        });
        ImageButton cheeky = (ImageButton) findViewById(R.id.cheeky);
        cheeky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(2);
                newFragment.show(ft,"Dialog");
            }
        });
        ImageButton cool = (ImageButton) findViewById(R.id.cool);
        cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(1);
                newFragment.show(ft,"Dialog");
            }
        });
        ImageButton disaster = (ImageButton) findViewById(R.id.disaster);
        disaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(8);
                newFragment.show(ft,"Dialog");
            }
        });
        ImageButton mellow = (ImageButton) findViewById(R.id.mellow);
        mellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(4);
                newFragment.show(ft,"Dialog");
            }
        });
        ImageButton nothingtoocray = (ImageButton) findViewById(R.id.nothingtoocray);
        nothingtoocray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(5);
                newFragment.show(ft,"Dialog");
            }
        });
        ImageButton surpriseme = (ImageButton) findViewById(R.id.surpriseme);
        surpriseme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(3);
                newFragment.show(ft,"Dialog");
            }
        });
        ImageButton tired = (ImageButton) findViewById(R.id.tired);
        tired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = FragmentActivity.newInstance(6);
                newFragment.show(ft,"Dialog");
            }
        });


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

