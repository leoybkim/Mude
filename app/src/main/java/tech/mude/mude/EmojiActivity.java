package com.example.faizaanmadhani.emojimenu;

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


public class EmojiActivity extends AppCompatActivity {
    public class MyActivity extends Activity {
        protected void onCreate(Bundle icicle) {
            super.onCreate(icicle);

            setContentView(R.layout.activity_main);

            ImageButton angry = (ImageButton) findViewById(R.id.button_id);
            angry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Angry.class));
                }
            });

            ImageButton annoyed = (ImageButton) findViewById(R.id.button_id);
            annoyed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Annoyed.class));
                }
            });

            ImageButton cheeky = (ImageButton) findViewById(R.id.button_id);
            cheeky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Cheeky.class));
                }
            });
            ImageButton cool = (ImageButton) findViewById(R.id.button_id);
            cool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Cool.class));
                }
            });
            ImageButton disaster = (ImageButton) findViewById(R.id.button_id);
            disaster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Disaster.class));
                }
            });
            ImageButton mellow = (ImageButton) findViewById(R.id.button_id);
            mellow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Mellow.class));
                }
            });
            ImageButton nothingTooCray = (ImageButton) findViewById(R.id.button_id);
            nothingTooCray.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, NothingTooCray.class));
                }
            });
            ImageButton sad = (ImageButton) findViewById(R.id.button_id);
            sad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Sad.class));
                }
            });
            ImageButton surpriseme = (ImageButton) findViewById(R.id.button_id);
            surpriseme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, SurpriseMe.class));
                }
            });
            ImageButton tired = (ImageButton) findViewById(R.id.button_id);
            tired.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EmojiActivity.this, Tired.class));
                }
            });
        }
    }
}


