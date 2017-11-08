package com.example.leebeomwoo.viewbody_final.Support;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
import com.example.leebeomwoo.viewbody_final.R;
import com.squareup.picasso.Picasso;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * Created by LeeBeomWoo on 2017-06-21.
 */

public class RecyclerviewClickEvent /**implements View.OnTouchListener*/ {
    Context context;
    Drawable drawable ;
    private int mActivePointerId = INVALID_POINTER_ID;
    public void Click(ListDummyItem ldItem, Context context){
        this.context = context;
        //read your lovely variable
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_detail);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        TextView txtViewTitle = (TextView) dialog.findViewById(R.id.detile_Title);
        ImageView imgViewIcon = (ImageView) dialog.findViewById(R.id.detile_Image);
        TextView video_title_1 = (TextView) dialog.findViewById(R.id.video_title_1);
        TextView video_title_2 = (TextView) dialog.findViewById(R.id.video_title_2);
        TextView video_title_3 = (TextView) dialog.findViewById(R.id.video_title_3);
        WebView imgViewFace = (WebView) dialog.findViewById(R.id.detile_face);
        WebView videoView_1 = (WebView) dialog.findViewById(R.id.video_view_1);
        WebView videoView_2 = (WebView) dialog.findViewById(R.id.video_view_2);
        TextView txtViewId = (TextView) dialog.findViewById(R.id.detile_Id);
        Button button = (Button) dialog.findViewById(R.id.like_btn);
        WebView videoView_3 = (WebView) dialog.findViewById(R.id.video_view_3);
        ImageView titleimage = (ImageView) dialog.findViewById(R.id.itemtitle_image);
        titleimage.setImageDrawable(titlecategory(ldItem.getLd_Category()));
        String result = ldItem.getLd_ImageUrl().replaceAll("\\/","/");
        Picasso.with(context).load(ConAdapter.SERVER_URL + result).resize(1500,2420).into(imgViewIcon);
        txtViewTitle.setText(ldItem.getLd_Title());
        txtViewId.setText(ldItem.getLd_Id());
        imgViewIcon.setFocusable(false);
        imgViewFace.setFocusable(false);
        imgViewFace.getSettings().setJavaScriptEnabled(true);
        imgViewFace.getSettings().setDomStorageEnabled(true);
        imgViewFace.getSettings().setUseWideViewPort(true);
        imgViewFace.getSettings().setLoadWithOverviewMode(true);
        imgViewFace.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        videoView_1.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        videoView_2.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        videoView_3.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        if (Build.VERSION.SDK_INT >= 19) {
            imgViewIcon.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            imgViewFace.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            videoView_1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            videoView_2.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            videoView_3.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            imgViewIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            imgViewFace.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            videoView_1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            videoView_2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            videoView_3.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        dialog.show();
    }
    private Drawable titlecategory(int i){
        switch (i){
            case 31://근육
                drawable = context.getResources().getDrawable(R.drawable.muscleup_body);
                break;
            case 32://골격
                drawable = context.getResources().getDrawable(R.drawable.bone);
                break;
            case 33://근지구력
                drawable = context.getResources().getDrawable(R.drawable.muscletime);
                break;
            case 34://근파워
                drawable = context.getResources().getDrawable(R.drawable.musclepower);
                break;
            case 35://머슬업
                drawable = context.getResources().getDrawable(R.drawable.muscleup_body);
                break;
            case 42://허리
                drawable = context.getResources().getDrawable(R.drawable.spine);
                break;
            case 11://전면상완
                drawable = context.getResources().getDrawable(R.drawable.front_upper_arm);
                break;
            case 21://후면상완
                drawable = context.getResources().getDrawable(R.drawable.back_upper_arm);
                break;
            case 12://전면하완
                drawable = context.getResources().getDrawable(R.drawable.front_lower_arm);
                break;
            case 22://후면하완
                drawable = context.getResources().getDrawable(R.drawable.back_lower_arm);
                break;
            case 37://복부
                drawable = context.getResources().getDrawable(R.drawable.stormach);
                break;
            case 38://가슴
                drawable = context.getResources().getDrawable(R.drawable.burst);
                break;
            case 13://전면어깨
                drawable = context.getResources().getDrawable(R.drawable.front_shoulder);
                break;
            case 23://후면어깨
                drawable = context.getResources().getDrawable(R.drawable.back_shoulder);
                break;
            case 14://전면목
                drawable = context.getResources().getDrawable(R.drawable.front_neck);
                break;
            case 24://후면목
                drawable = context.getResources().getDrawable(R.drawable.back_neck);
                break;
            case 15://전면허벅지
                drawable = context.getResources().getDrawable(R.drawable.front_upper_leg);
                break;
            case 25://후면허벅지
                drawable = context.getResources().getDrawable(R.drawable.back_upper_leg);
                break;
            case 16://전면종아리
                drawable = context.getResources().getDrawable(R.drawable.front_lower_leg);
                break;
            case 26://후면종아리
                drawable = context.getResources().getDrawable(R.drawable.back_lower_leg);
                break;
            case 36://엉덩이
                drawable = context.getResources().getDrawable(R.drawable.hip);
                break;
            case 39://상체
                drawable = context.getResources().getDrawable(R.drawable.upper);
                break;
            case 40://하체
                drawable = context.getResources().getDrawable(R.drawable.lower);
                break;
            case 17://전면몸통
                drawable = context.getResources().getDrawable(R.drawable.stormach);
                break;
            case 27://후면몸통
                drawable = context.getResources().getDrawable(R.drawable.back_body);
                break;
            case 41://심폐지구력
                drawable = context.getResources().getDrawable(R.drawable.cardiopulmonary_endurance);
                break;
            case 19://미정
                drawable = context.getResources().getDrawable(R.drawable.foodlogo);
                break;
        }
        return drawable;
    }
/**
    @Override
    public boolean onTouch(View view, MotionEvent ev) {

        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);

        final int action = MotionEventCompat.getActionMasked(ev);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(ev, mActivePointerId);

                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                mPosX += dx;
                mPosY += dy;

                invalidate();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex);
                    mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                }
                break;
            }
        }
        return true;
    }
 */
}
