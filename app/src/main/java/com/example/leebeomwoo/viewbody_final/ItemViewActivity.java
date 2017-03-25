package com.example.leebeomwoo.viewbody_final;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.leebeomwoo.viewbody_final.ItemGroup.ItemFragment;
import com.example.leebeomwoo.viewbody_final.ItemGroup.Item_follow_fragment;
import com.example.leebeomwoo.viewbody_final.ItemGroup.Item_follow_fragment_21;
import com.example.leebeomwoo.viewbody_final.ItemGroup.TrainerInfoFragment;

/**
 * Created by LBW on 2016-06-30.
 */
public class ItemViewActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public static final int REQUEST_CAMERA = 1;
    private Boolean isFabOpen = false;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    String tr_id, item_word;
    final Item_follow_fragment finalFollow_fragment = new Item_follow_fragment();
    final Item_follow_fragment_21 Follow_fragment = new Item_follow_fragment_21();
    int fragment, q, section, currentCameraId;
    boolean previewing = false;
    ;
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
            if (Build.VERSION.SDK_INT >= 21) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.relativeLayout,
                        Item_follow_fragment_21.newInstance(tr_id, message, section));
                fragmentTransaction.commit();
            } else {
                Item_follow_fragment itemFragment = new Item_follow_fragment();
                itemFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.relativeLayout, itemFragment); // Activity 레이아웃의 View ID
                fragmentTransaction.commit();
            }
        }
        // Now later we can lookup the fragment by tag

    }

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, Camera camera) {
        Camera.CameraInfo info =
                new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
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
}
