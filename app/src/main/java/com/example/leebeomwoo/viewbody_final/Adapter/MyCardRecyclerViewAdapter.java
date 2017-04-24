package com.example.leebeomwoo.viewbody_final.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.CardItem;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCardRecyclerViewAdapter extends RecyclerView.Adapter<MyCardRecyclerViewAdapter.ViewHolder> {

    List<CardItem> cardItems = new ArrayList<>();
    Context bContext;



    public MyCardRecyclerViewAdapter(Context context, List<CardItem> cardItemList){
        this.cardItems = cardItemList;
        this.bContext = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mView;
        public final TextView txtNick;
        public final TextView txtViewCategory;
        public final WebView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mView = (CardView) itemLayoutView.findViewById(R.id.card_Card);
            txtNick = (TextView) itemLayoutView.findViewById(R.id.nickname_txtB);
            txtViewCategory = (TextView) itemLayoutView.findViewById(R.id.category_txtB);
            imgViewIcon = (WebView) itemLayoutView.findViewById(R.id.faceimage);
            imgViewIcon.setFocusable(false);
            imgViewIcon.getSettings().setJavaScriptEnabled(true);
            imgViewIcon.getSettings().setDomStorageEnabled(true);
            imgViewIcon.getSettings().setUseWideViewPort(true);
            imgViewIcon.getSettings().setLoadWithOverviewMode(true);
            if (Build.VERSION.SDK_INT >= 19) {
                imgViewIcon.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
            else {
                imgViewIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final CardItem cardItem = cardItems.get(position);
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.txtNick.setText(cardItem.getLd_Id());
        viewHolder.txtViewCategory.setText(cardItem.getCategory());
        //  viewHolder.imgViewIcon.loadUrl(ConAdapter.SERVER_URL + bdItem.getBd_ImageUrl()); 실제 구동시
        viewHolder.imgViewIcon.loadUrl(ConAdapter.SERVER_URL + "data_image/" + cardItem.getImageUrl());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //트레이너와 영양사들의 개인정보로 연결되도록 세팅
            }
        });
    }
    @Override
    public int getItemCount() {

        return (null != cardItems ? cardItems.size() : 0);
    }

    public void setCardItems (List<CardItem> cardItems1) {
        cardItems.clear();
        this.cardItems = cardItems1;
    }
}
