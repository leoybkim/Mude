package tech.mude.mude.activities;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import tech.mude.mude.R;

import static com.estimote.sdk.EstimoteSDK.getApplicationContext;

/**
 * Created by Barick on 2017-03-26.
 */

public class FragmentActivity extends DialogFragment{
    int num;
    String quote;
    String uri;

    static FragmentActivity newInstance(int num) {
        FragmentActivity f = new FragmentActivity();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        num = getArguments().getInt("num");

        Random ran = new Random();
        int i = ran.nextInt(1);
        //switch statement to decide which statement
        switch (num) {
            case 1:
                uri = "spotify:user:spotify:playlist:1JCZJ9vKg2r8eBaBLz14MT";
                if(i==1){
                    quote = "What's a golf club's favourite type of music? Swing.";
                }
                else {
                    quote = "Parallel lines have so much in common. It's a shame they'll never meet.";
                }
                break;
            case 2:
                uri = "spotify:user:spotify:playlist:4TNBeyX7awz89qwtTmh9D4";
                if(i==1){
                    quote = "If Watermelon exists, why doesn't Earthmelon, Firemelon, and Airmelon? The Elemelons.";
                }
                else {
                    quote = "You have to be odd to be number one.";
                }
                break;
            case 3:
                uri = "spotify:user:spotify:playlist:3xgbBiNc7mh3erYsCl8Fwg";
                if(i==1){
                    quote = "Never discuss infinity with a mathematician. You’ll never hear the end of it.";
                }
                else {
                    quote = "What do you call a fake noodle? An impasta.";
                }
                break;
            case 4:
                uri = "spotify:user:spotify:playlist:1JCZJ9vKg2r8eBaBLz14MT";
                if(i==1){
                    quote = "To succeed in life, you need three things: a wishbone, a backbone, and a funny bone.";
                }
                else {
                    quote = "Denial, anger, bargaining, depression, acceptance. The five stages of waking up.";
                }
                break;
            case 5:
                uri = "spotify:user:spotify:playlist:67nMZWgcUxNa5uaiyLDR2x";
                if(i==1){
                    quote = "Based on how I react when toast pops out of the toaster, I will never look cool walking away from an explosion";
                }
                else {
                    quote = "Hush little laptop don’t you cry, mama’s gonna find you some more wifi";
                }
                break;
            case 6:
                uri = "spotify:user:1231600851:playlist:69vPT2Kotp6Mxc98tz1Ut6";
                if(i==1){
                    quote = "Life is not about waiting for the storm to pass but learning to dance in the rain.";
                }
                else {
                    quote = "Difficult roads often lead to beautiful destinations.";
                }
                break;
            case 7:
                uri = "spotify:user:sonymusic:playlist:6dm9jZ2p8iGGTLre7nY4hf";
                if(i==1){
                    quote = "Let’s all take a moment and be thankful spiders can’t fly";
                }
                else {
                    quote = "I don’t trip. I do random gravity checks.";
                }
                break;
            case 8:
                uri= "spotify:user:spotify:playlist:1JCZJ9vKg2r8eBaBLz14MT";
                if(i==1){
                    quote = "The secret of change is to focus all of your energy, not on fighting old, but on building the new.";
                }
                else {
                    quote = "Life has ups and downs. Ride the downs as if you were on a roller coaster and take the ups like being on the top of a mountain.";
                }
                break;
            case 9:
                uri = "spotify:user:topsify:playlist:6Qf2sXTjlH3HH30Ijo6AUp";
                if(i==1){
                    quote = "Did you hear about the two guys that stole a calendar? They each got six months.";
                }
                else {
                    quote = "If someone calls you ‘ugly’, have a good comeback and reply, ‘excuse me, I am not a mirror.'";
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragement_dialog, container, false);
        View tv = v.findViewById(R.id.textView2);
        ((TextView)tv).setText(quote);

        return v;

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
        intent.setData(Uri.parse(uri));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);

    }



}
