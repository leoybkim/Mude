package tech.mude.mude.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import io.indico.Indico;
import io.indico.network.IndicoCallback;
import io.indico.results.IndicoResult;
import io.indico.utils.IndicoException;
import tech.mude.mude.R;

/**
 * Created by leo on 25/03/17.
 */

public class MLActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);

        Toast.makeText(this, "opened", Toast.LENGTH_LONG).show();

        Indico.init(this, getString(R.string.indico_api), null);

        try {
            Indico.sentiment.predict("indico is so easy to use!", new IndicoCallback<IndicoResult>() {
                @Override public void handle(IndicoResult result) throws IndicoException {
                    Log.i("Indico Sentiment", "sentiment of: " + result.getSentiment());
                }
            });
        } catch (IOException | IndicoException e) {
            e.printStackTrace();
        }
    }
}
