package com.example.leebeomwoo.viewbody_final.Support;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
import com.example.leebeomwoo.viewbody_final.R;

/**
 * Created by LeeBeomWoo on 2017-06-21.
 */

public class RecyclerviewClickEvent {

    public void Click(ListDummyItem ldItem, Context context){
        //read your lovely variable
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_detail);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        TextView txtViewTitle = (TextView) dialog.findViewById(R.id.detile_Title);
        HelpWebView imgViewIcon = (HelpWebView) dialog.findViewById(R.id.detile_Image);
        TextView video_title_1 = (TextView) dialog.findViewById(R.id.video_title_1);
        TextView video_title_2 = (TextView) dialog.findViewById(R.id.video_title_2);
        TextView video_title_3 = (TextView) dialog.findViewById(R.id.video_title_3);
        WebView imgViewFace = (WebView) dialog.findViewById(R.id.detile_face);
        WebView videoView_1 = (WebView) dialog.findViewById(R.id.video_view_1);
        WebView videoView_2 = (WebView) dialog.findViewById(R.id.video_view_2);
        TextView txtViewId = (TextView) dialog.findViewById(R.id.detile_Id);
        Button button = (Button) dialog.findViewById(R.id.like_btn);
        WebView videoView_3 = (WebView) dialog.findViewById(R.id.video_view_3);
        txtViewTitle.setText(ldItem.getLd_Title());
        imgViewIcon.loadUrl(ConAdapter.SERVER_URL + ldItem.getLd_ImageUrl());
        txtViewId.setText(ldItem.getLd_Id());
        imgViewIcon.setFocusable(false);
        imgViewIcon.getSettings().setJavaScriptEnabled(true);
        imgViewIcon.getSettings().setDomStorageEnabled(true);
        imgViewIcon.getSettings().setUseWideViewPort(true);
        imgViewIcon.getSettings().setLoadWithOverviewMode(true);
        imgViewIcon.getSettings().setBuiltInZoomControls(true);
        imgViewFace.setFocusable(false);
        imgViewFace.getSettings().setJavaScriptEnabled(true);
        imgViewFace.getSettings().setDomStorageEnabled(true);
        imgViewFace.getSettings().setUseWideViewPort(true);
        imgViewFace.getSettings().setLoadWithOverviewMode(true);
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
}
