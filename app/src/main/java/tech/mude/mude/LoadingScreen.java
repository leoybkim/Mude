package tech.mude.mude;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Barick on 2017-03-25.
 */

public class LoadingScreen extends Activity{
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
    }
}
