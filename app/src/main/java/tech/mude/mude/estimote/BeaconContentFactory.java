package tech.mude.mude.estimote;

public interface BeaconContentFactory {

    void getContent(tech.mude.mude.estimote.BeaconID beaconID, Callback callback);

    interface Callback {
        void onContentReady(Object content);
    }
}
