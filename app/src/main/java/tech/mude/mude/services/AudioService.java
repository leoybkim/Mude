package tech.mude.mude.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import tech.mude.mude.estimote.BeaconID;
import tech.mude.mude.estimote.EstimoteCloudBeaconDetails;
import tech.mude.mude.estimote.EstimoteCloudBeaconDetailsFactory;
import tech.mude.mude.estimote.NearestBeaconManager;
import tech.mude.mude.estimote.ProximityContentManager;

/**
 * Created by leo on 25/03/17.
 */

public class AudioService extends Service {

    // Estimote managers and variables
    private static BeaconManager mBeaconManager;
    private ProximityContentManager mProximityContentManager;
    private Beacon mNearestBeacon;
    private NearestBeaconManager mNearestBeaconManager;
    private List<BeaconID> beaconIDs;
    private BeaconID currentlyNearestBeaconID;
    private boolean firstEventSent = false;
    private NearestBeaconManager.Listener listener;

    // Distance variables
    private double distance;
    private ArrayList<Double> distanceArray = new ArrayList<>();
    private double average = 0;
    int volume = 0;

    // Volume control
    AudioManager am;

    private Context mContext;

    private static String TAG = AudioService.class.getSimpleName();

    public AudioService(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProximityContentManager.destroy();
    }

    public AudioService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Setup token
        EstimoteSDK.initialize(this, "beacons-wearhacks-gmail-co-fm7", "0d11e8a3491c51a383ab8c85b55929b7");
        beaconIDs = Arrays.asList(
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 33491, 34365));


        // Setup Beacon Manager
        mBeaconManager = new BeaconManager(getApplicationContext());
        mBeaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 0);
        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                mBeaconManager.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        null, null));
            }
        });

        mBeaconManager.setMonitoringListener(new BeaconManager.MonitoringListener(){
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                mNearestBeacon = list.get(0);
            }

            @Override
            public void onExitedRegion(Region region) {

            }
        });

        // Setup ProximityContentManager
        mProximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 33491, 34365)),
                new EstimoteCloudBeaconDetailsFactory());

        mProximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {
                String text;
                Integer backgroundColor;
                if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;
                    text = "You're in " + beaconDetails.getBeaconName() + "'s range!";
                    Log.d(TAG, text);
                } else {
                    text = "No beacons in range.";
                    backgroundColor = null;
                    Log.d(TAG, text);
                }
            }
        });

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

       mNearestBeaconManager = new NearestBeaconManager(this,
                Arrays.asList(new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 33491, 34365)));

        final Region ALL_ESTIMOTE_BEACONS = new Region("all Estimote beacons", null, null, null);
        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                mBeaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
            }
        });
        mBeaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                checkForNearestBeacon(list);
            }
        });

        // Takes average every second
        timer();
    }

    // TODO: clean up later
    private void checkForNearestBeacon(List<Beacon> allBeacons) {
        List<Beacon> beaconsOfInterest = filterOutBeaconsByIDs(allBeacons, beaconIDs);
        Beacon nearestBeacon = findNearestBeacon(beaconsOfInterest);
        if (nearestBeacon != null) {
            BeaconID nearestBeaconID = BeaconID.fromBeacon(nearestBeacon);
            if (!nearestBeaconID.equals(currentlyNearestBeaconID) || !firstEventSent) {
                updateNearestBeacon(nearestBeaconID);
            }
        } else if (currentlyNearestBeaconID != null || !firstEventSent) {
            updateNearestBeacon(null);
        }

        if (nearestBeacon != null) {
            // Saves distance between estimote and phone
            distance = Utils.computeAccuracy(nearestBeacon);
            // Appends distance value to array
            distanceArray.add(distance);
        }
    }

    private void updateNearestBeacon(BeaconID beaconID) {
        currentlyNearestBeaconID = beaconID;
        firstEventSent = true;
        if (listener != null) {
            listener.onNearestBeaconChanged(beaconID);
        }
    }

    private static List<Beacon> filterOutBeaconsByIDs(List<Beacon> beacons, List<BeaconID> beaconIDs) {
        List<Beacon> filteredBeacons = new ArrayList<>();
        for (Beacon beacon : beacons) {
            BeaconID beaconID = BeaconID.fromBeacon(beacon);
            if (beaconIDs.contains(beaconID)) {
                filteredBeacons.add(beacon);
            }
        }
        return filteredBeacons;
    }

    private static Beacon findNearestBeacon(List<Beacon> beacons) {
        Beacon nearestBeacon = null;
        double nearestBeaconsDistance = -1;
        for (Beacon beacon : beacons) {
            double distance = Utils.computeAccuracy(beacon);
            if (distance > -1 &&
                    (distance < nearestBeaconsDistance || nearestBeacon == null)) {
                nearestBeacon = beacon;
                nearestBeaconsDistance = distance;
            }
        }

//        Log.d(TAG, "Nearest beacon: " + nearestBeacon + ", distance: " + nearestBeaconsDistance);
        return nearestBeacon;
    }


    // TODO: modularize this code
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public void timer() {
        final Runnable exec = new Runnable() {
            public void run() {

                //Take average of distance
                double sum = 0;
                for (double distance: distanceArray) {
                    sum += distance;
                }
                average = (sum / distanceArray.size());
                volume = (int) (3 + average*3);

                Log.d(TAG, String.valueOf(volume));

                am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
                distanceArray.clear();
            }
        };
        //Calls the runnable function every second with a 0 second delay
        scheduler.scheduleAtFixedRate(exec, 0, 3, TimeUnit.SECONDS);
    }
}
