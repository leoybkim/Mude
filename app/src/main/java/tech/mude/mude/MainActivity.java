package tech.mude.mude;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.Utils;
import com.estimote.sdk.cloud.model.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import tech.mude.mude.estimote.BeaconID;
import tech.mude.mude.estimote.EstimoteCloudBeaconDetails;
import tech.mude.mude.estimote.EstimoteCloudBeaconDetailsFactory;
import tech.mude.mude.estimote.NearestBeaconManager;
import tech.mude.mude.estimote.ProximityContentManager;

public class MainActivity extends AppCompatActivity {

    private static BeaconManager mBeaconManager;
    private ProximityContentManager mProximityContentManager;
    private Beacon mNearestBeacon;
    private TextView mProximity;
    private NearestBeaconManager mNearestBeaconManager;
    private List<BeaconID> beaconIDs;
    private BeaconID currentlyNearestBeaconID;
    private boolean firstEventSent = false;
    private NearestBeaconManager.Listener listener;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final Map<Color, Integer> BACKGROUND_COLORS = new HashMap<>();

    static {
        BACKGROUND_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BACKGROUND_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BACKGROUND_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    private static final int BACKGROUND_COLOR_NEUTRAL = android.graphics.Color.rgb(160, 169, 172);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup token
        EstimoteSDK.initialize(this, "beacons-wearhacks-gmail-co-fm7", "0d11e8a3491c51a383ab8c85b55929b7");
        beaconIDs = Arrays.asList(
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 33491, 34365));

        // Setup views
        mProximity = (TextView) findViewById(R.id.proximity);

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

//        mBeaconManager.setRangingListener(new BeaconManager.RangingListener() {
//            @Override
//            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
//                if (!list.isEmpty()) {
//                    mNearestBeacon = list.get(0);
//                    mProximity.setText(String.valueOf(mNearestBeacon.getRssi()));
//                    Log.d(TAG, String.valueOf(mNearestBeacon.getRssi()));
//                }
//            }
//        });

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
                    backgroundColor = BACKGROUND_COLORS.get(beaconDetails.getBeaconColor());

                } else {
                    text = "No beacons in range.";
                    backgroundColor = null;
                }
                ((TextView) findViewById(R.id.textView)).setText(text);
                findViewById(R.id.relativeLayout).setBackgroundColor(
                        backgroundColor != null ? backgroundColor : BACKGROUND_COLOR_NEUTRAL);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else {
            Log.d(TAG, "Starting ProximityContentManager content updates");
//            mProximityContentManager.startContentUpdates();
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
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Stopping ProximityContentManager content updates");
        mProximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProximityContentManager.destroy();
    }

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

        mProximity.setText(String.valueOf(Utils.computeAccuracy(nearestBeacon)));
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
}
