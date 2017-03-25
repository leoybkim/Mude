package tech.mude.mude;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import static android.media.AudioManager.FLAG_SHOW_UI;

/**
 * Created by Barick on 2017-03-25.
 */

public class VolControl extends Activity {
    private AudioManager audioManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
    }

    private void initControls()
    {
        int val = 1;
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, val, 1 << 5 );

    }
}
