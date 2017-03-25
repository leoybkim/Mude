package tech.mude.mude.estimote;


import java.util.HashMap;
import java.util.Map;

public class BeaconContentCache implements tech.mude.mude.estimote.BeaconContentFactory {

    private tech.mude.mude.estimote.BeaconContentFactory beaconContentFactory;

    private Map<tech.mude.mude.estimote.BeaconID, Object> cachedContent = new HashMap<>();

    public BeaconContentCache(tech.mude.mude.estimote.BeaconContentFactory beaconContentFactory) {
        this.beaconContentFactory = beaconContentFactory;
    }

    @Override
    public void getContent(final tech.mude.mude.estimote.BeaconID beaconID, final Callback callback) {
        if (cachedContent.containsKey(beaconID)) {
            callback.onContentReady(cachedContent.get(beaconID));
        } else {
            beaconContentFactory.getContent(beaconID, new Callback() {
                @Override
                public void onContentReady(Object content) {
                    cachedContent.put(beaconID, content);
                    callback.onContentReady(content);
                }
            });
        }
    }
}
