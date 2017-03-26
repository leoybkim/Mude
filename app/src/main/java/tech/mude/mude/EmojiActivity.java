package com.example.faizaanmadhani.emojimenu;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageButton;
import android.view.View;
import android.content.Intent;

import com.example.faizaanmadhani.emojimenu.Emotions.Angry;
import com.example.faizaanmadhani.emojimenu.Emotions.Annoyed;
import com.example.faizaanmadhani.emojimenu.Emotions.Cheeky;
import com.example.faizaanmadhani.emojimenu.Emotions.Cool;
import com.example.faizaanmadhani.emojimenu.Emotions.Disaster;
import com.example.faizaanmadhani.emojimenu.Emotions.Mellow;
import com.example.faizaanmadhani.emojimenu.Emotions.NothingTooCray;
import com.example.faizaanmadhani.emojimenu.Emotions.Sad;
import com.example.faizaanmadhani.emojimenu.Emotions.SurpriseMe;
import com.example.faizaanmadhani.emojimenu.Emotions.Tired;

import tech.mude.mude.R;

import static android.provider.MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH;


public class EmojiActivity extends AppCompatActivity {
    public class MyActivity extends Activity {
        protected void onCreate(Bundle icicle) {
            super.onCreate(icicle);

            setContentView(R.layout.activity_main);

            ImageButton angry = (ImageButton) findViewById(R.id.angry);
            angry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Angry.class));
                }
            });

            ImageButton annoyed = (ImageButton) findViewById(R.id.annoyed);
            annoyed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Annoyed.class));
                }
            });

            ImageButton cheeky = (ImageButton) findViewById(R.id.cheeky);
            cheeky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Cheeky.class));
                }
            });
            ImageButton cool = (ImageButton) findViewById(R.id.cool);
            cool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Disaster.class));
                }
            });
            ImageButton mellow = (ImageButton) findViewById(R.id.mellow);
            mellow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Mellow.class));
                }
            });
            ImageButton nothingTooCray = (ImageButton) findViewById(R.id.nothingtoocray);
            nothingTooCray.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, NothingTooCray.class));
                }
            });
            ImageButton sad = (ImageButton) findViewById(R.id.sad);
            sad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Sad.class));
                }
            });
            ImageButton surpriseme = (ImageButton) findViewById(R.id.surpriseme);
            surpriseme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, SurpriseMe.class));
                }
            });
            ImageButton tired = (ImageButton) findViewById(R.id.tired);
            tired.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Tired.class));
                }
            });
        }
    }
}


