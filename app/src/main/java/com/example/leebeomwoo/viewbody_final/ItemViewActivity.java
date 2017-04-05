package com.example.leebeomwoo.viewbody_final;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.leebeomwoo.viewbody_final.ItemGroup.ItemFragment;
import com.example.leebeomwoo.viewbody_final.ItemGroup.Item_follow_fragment;
import com.example.leebeomwoo.viewbody_final.ItemGroup.Item_follow_fragment_21;

/**
 * Created by LBW on 2016-06-30.
 */
public class ItemViewActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public static final int REQUEST_CAMERA = 1;
    private Boolean isFabOpen = false;
    FloatingActionButton fab, fab1, fab2, fab3, fab4, fab5;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    String tr_id, item_word;
    final Item_follow_fragment finalFollow_fragment = new Item_follow_fragment();
    final Item_follow_fragment_21 Follow_fragment = new Item_follow_fragment_21();
    int fragment, q, section, currentCameraId;
    boolean previewing = false;;
    public boolean recording, pausing, inPreview;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        String message = intent.getStringExtra("itemUrl");
        fragment = intent.getIntExtra("page_num", 1);
        tr_id = intent.getStringExtra("tr_Id");
        section = intent.getIntExtra("section", 1);

        // item_word = intent.getStringExtra("item_word");
        Bundle bundle = new Bundle();
        bundle.putString("itemUrl", message);
        bundle.putString("tr_Id", tr_id);
        bundle.putInt("section", section);

        FragmentManager fragmentManager = getFragmentManager();
        if (fragment != 3) {
            ItemFragment itemFragment = new ItemFragment();
            itemFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, itemFragment); // Activity 레이아웃의 View ID
            fragmentTransaction.commit();
        } else {
            if (Build.VERSION.SDK_INT >=21) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment,
                        Item_follow_fragment_21.newInstance(tr_id, message, section));
                fragmentTransaction.commit();
            } else {
                Item_follow_fragment itemFragment = new Item_follow_fragment();
                itemFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment, itemFragment); // Activity 레이아웃의 View ID
                fragmentTransaction.commit();
            }
        }
            // Now later we can lookup the fragment by tag
/**
        fab1.setSize(FloatingActionButton.SIZE_MINI);
        fab2.setSize(FloatingActionButton.SIZE_MINI);
        fab3.setSize(FloatingActionButton.SIZE_MINI);
        fab4.setSize(FloatingActionButton.SIZE_MINI);
        fab5.setSize(FloatingActionButton.SIZE_MINI);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.iteammainfabclose);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.iteammainfabopen);
        fabImageset(fragment);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);
 **/
    }
    /**
    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.fab :
                animateFAB();
                break;
            case R.id.fab1 :
                switch (fragment) {
                    case 1:
                        Intent intent_1 = new Intent(ItemViewActivity.this, MainActivity.class);
                        q = 1;
                        //intent_1.putExtra("item_word", item_word);
                        intent_1.putExtra("fragment", q);
                        startActivity(intent_1);
                        break;
                    case 2:
                        Intent intent_2 = new Intent(ItemViewActivity.this, MainActivity.class);
                        q = 0;
                        //intent_2.putExtra("item_word", item_word);
                        intent_2.putExtra("fragment", q);
                        startActivity(intent_2);
                        break;
                    case 3:
                        if (Build.VERSION.SDK_INT >= 21) {
                            // Stop recording and finish
                            if (pausing) {
                                Follow_fragment.onResume();
                                pausing = false;
                            } else {
                                Follow_fragment.onPause();
                                pausing = true;
                            }
                        } else {
                            if (pausing) {
                                finalFollow_fragment.onResume();
                                pausing = false;
                            } else {
                                finalFollow_fragment.onPause();
                                pausing = true;
                            }
                            finalFollow_fragment.mediaRecorder.start();
                        }
                        fab1.setIcon(R.drawable.playbutton);
                        fab.startAnimation(rotate_backward);
                        fab1.startAnimation(fab_close);
                        fab2.startAnimation(fab_close);
                        fab3.startAnimation(fab_close);
                        fab4.startAnimation(fab_close);
                        fab5.startAnimation(fab_close);
                        fab.setClickable(false);
                        fab1.setClickable(false);
                        fab2.setClickable(false);
                        fab3.setClickable(false);
                        fab4.setClickable(false);
                        fab5.setClickable(false);
                        isFabOpen = false;
                        fab.setVisibility(View.INVISIBLE);
                        break;
                }
                break;
            case R.id.fab2 :
                switch (fragment) {
                    case 1:
                        Intent intent_1 = new Intent(ItemViewActivity.this, MainActivity.class);
                        q = 2;
                        //intent_1.putExtra("item_word", item_word);
                        intent_1.putExtra("fragment", q);
                        startActivity(intent_1);
                        break;
                    case 2:
                        Intent intent_2 = new Intent(ItemViewActivity.this, MainActivity.class);
                        q = 2;
                        //intent_2.putExtra("item_word", item_word);
                        intent_2.putExtra("fragment", q);
                        startActivity(intent_2);
                        break;
                    case 3:
                        if (Build.VERSION.SDK_INT >= 21) {
                            // Stop recording and finish
                            if (Follow_fragment.mIsRecordingVideo) {
                                Follow_fragment.stopRecordingVideo();
                            } else {
                                Follow_fragment.startRecordingVideo();
                            }
                        } else {
                            if (recording){
                                finalFollow_fragment.mediaRecorder.stop();
                                finalFollow_fragment.mediaRecorder.release();
                                finalFollow_fragment.mediaRecorder.reset();
                                recording = false;
                            } else{
                                finalFollow_fragment.mediaRecorder.start();
                                recording = true;
                                fab1.setIcon(R.drawable.record);
                            }
                            finalFollow_fragment.mediaRecorder.start();
                        }
                        fab1.setIcon(R.drawable.record);
                }
                fab.startAnimation(rotate_backward);
                fab1.startAnimation(fab_close);
                fab2.startAnimation(fab_close);
                fab3.startAnimation(fab_close);
                fab4.startAnimation(fab_close);
                fab5.startAnimation(fab_close);
                fab.setClickable(false);
                fab1.setClickable(false);
                fab2.setClickable(false);
                fab3.setClickable(false);
                fab4.setClickable(false);
                fab5.setClickable(false);
                isFabOpen = false;
                fab.setVisibility(View.INVISIBLE);
                break;
            case R.id.fab3 :
                switch (fragment) {
                    case 1:
                        Intent intent_1 = new Intent(ItemViewActivity.this, MainActivity.class);
                        q = 3;
                        //intent_1.putExtra("item_word", item_word);
                        intent_1.putExtra("fragment", q);
                        startActivity(intent_1);
                        break;
                    case 2:
                        Intent intent_2 = new Intent(ItemViewActivity.this, MainActivity.class);
                        q = 3;
                        //intent_2.putExtra("item_word", item_word);
                        intent_2.putExtra("fragment", q);
                        startActivity(intent_2);
                        break;
                    case 3:
                        if (Build.VERSION.SDK_INT >= 21) {
                            Follow_fragment.switchCamera();
                        } else {
                            setCameraDisplayOrientation(ItemViewActivity.this, currentCameraId, finalFollow_fragment.mCamera);
                            Thread t_kit = new Thread() {
                                public void run() {
                                    //myViewer is the SurfaceView object which uses
                                    //the camera
                                    finalFollow_fragment.flipit();
                                }
                            };
                            t_kit.start();
                        }
                        break;
                }
                fab.startAnimation(rotate_backward);
                fab1.startAnimation(fab_close);
                fab2.startAnimation(fab_close);
                fab3.startAnimation(fab_close);
                fab4.startAnimation(fab_close);
                fab5.startAnimation(fab_close);
                fab.setClickable(false);
                fab1.setClickable(false);
                fab2.setClickable(false);
                fab3.setClickable(false);
                fab4.setClickable(false);
                fab5.setClickable(false);
                isFabOpen = false;
                fab.setVisibility(View.INVISIBLE);
                break;
            case R.id.fab4 :
                timetreck timetreck = new timetreck();
                FragmentManager fragmentManager_1 = getFragmentManager();
                FragmentTransaction fragmentTransaction_1 = fragmentManager_1.beginTransaction();
                fragmentTransaction_1.replace(R.id.fragment, timetreck).commit();
                fab.startAnimation(rotate_backward);
                fab1.startAnimation(fab_close);
                fab2.startAnimation(fab_close);
                fab3.startAnimation(fab_close);
                fab4.startAnimation(fab_close);
                fab5.startAnimation(fab_close);
                fab.setClickable(false);
                fab1.setClickable(false);
                fab2.setClickable(false);
                fab3.setClickable(false);
                fab4.setClickable(false);
                fab5.setClickable(false);
                isFabOpen = false;
                fab.setVisibility(View.INVISIBLE);
                break;
            case R.id.fab5 :
                TrainerInfoFragment fragment3 = new TrainerInfoFragment();
                FragmentManager fragmentManager = getFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString("tr_id", tr_id);
                fragment3.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment3).commit();
                fab.startAnimation(rotate_backward);
                fab1.startAnimation(fab_close);
                fab2.startAnimation(fab_close);
                fab3.startAnimation(fab_close);
                fab4.startAnimation(fab_close);
                fab5.startAnimation(fab_close);
                fab.setClickable(false);
                fab1.setClickable(false);
                fab2.setClickable(false);
                fab3.setClickable(false);
                fab4.setClickable(false);
                fab5.setClickable(false);
                isFabOpen = false;
                fab.setVisibility(View.INVISIBLE);
                break;
        }
    }
    **/
    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, Camera camera) {
        Camera.CameraInfo info =
                new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
    public void animateFAB() {

        if (isFabOpen) {

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab4.startAnimation(fab_close);
            fab5.startAnimation(fab_close);
            fab.setClickable(true);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            fab5.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");
        } else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab5.startAnimation(fab_open);
            fab.setClickable(true);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            fab5.setClickable(true);
            isFabOpen = true;
            Log.d("Raj", "open");
        }
    }
/**
    private void fabImageset (int i) {
        switch (i){
            case 1:
                fab1.setIcon(R.drawable.followmelogo);
                fab2.setIcon(R.drawable.exerciselogo);
                fab3.setIcon(R.drawable.foodlogo);
                break;
            case 2:
                fab1.setIcon(R.drawable.followmelogo);
                fab2.setIcon(R.drawable.bodyinfologo);
                fab3.setIcon(R.drawable.foodlogo);
                break;
            case 3:
                fab1.setIcon(R.drawable.next);
                fab2.setIcon(R.drawable.record);
                fab3.setImageResource(R.drawable.switch_camera);
                break;
            case 4:
                fab1.setIcon(R.drawable.bodyinfologo);
                fab2.setIcon(R.drawable.exerciselogo);
                fab3.setIcon(R.drawable.followmelogo);
                break;

        }
    }
 **/
}
