package tech.mude.mude;


import android.support.v7.app.AppCompatActivity;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static android.R.attr.handle;

/**
 * Created by Barick on 2017-03-25.
 */

public class Time extends AppCompatActivity {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public void timer() {
        final Runnable exec = new Runnable() {
            public void run() {
                System.out.println("TEST"); //temp runnable command
                //Take average of distance
            }
        };
        final ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(exec, 0, 1, TimeUnit.SECONDS);
        //Calls the runnable function every second with a 0 second delay
    }
}

