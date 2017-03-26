package tech.mude.mude.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
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
        launchTestService();
        ImageButton annoyed = (ImageButton) findViewById(R.id.annoyed);
        annoyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:sonymusic:playlist:6dm9jZ2p8iGGTLre7nY4hf"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton angry = (ImageButton) findViewById(R.id.angry);
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:topsify:playlist:6Qf2sXTjlH3HH30Ijo6AUp"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton cheeky = (ImageButton) findViewById(R.id.cheeky);
        cheeky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:spotify:playlist:4TNBeyX7awz89qwtTmh9D4"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton cool = (ImageButton) findViewById(R.id.cool);
        cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:spotify:playlist:1JCZJ9vKg2r8eBaBLz14MT"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton disaster = (ImageButton) findViewById(R.id.disaster);
        disaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:spotify:playlist:1JCZJ9vKg2r8eBaBLz14MT"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton mellow = (ImageButton) findViewById(R.id.mellow);
        mellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:spotify:playlist:1JCZJ9vKg2r8eBaBLz14MT"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton nothingtoocray = (ImageButton) findViewById(R.id.nothingtoocray);
        nothingtoocray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:spotify:playlist:67nMZWgcUxNa5uaiyLDR2x"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton sad = (ImageButton) findViewById(R.id.mellow);
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:spotify:playlist:0Vib1QAMtMaiywa3QSEq40"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton surpriseme = (ImageButton) findViewById(R.id.surpriseme);
        surpriseme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:spotify:playlist:3xgbBiNc7mh3erYsCl8Fwg"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        ImageButton tired = (ImageButton) findViewById(R.id.tired);
        tired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(
                        "spotify:user:1231600851:playlist:69vPT2Kotp6Mxc98tz1Ut6"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
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

