package com.example.leebeomwoo.viewbody_final;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.CardFragment.OnListFragmentInteractionListener;
import com.example.leebeomwoo.viewbody_final.Item.CardItem;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCardRecyclerViewAdapter extends RecyclerView.Adapter<MyCardRecyclerViewAdapter.ViewHolder> {

    private final List<CardItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyCardRecyclerViewAdapter(List<CardItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCardView.loadUrl(mValues.get(position).getCard_ImageUrl());
        holder.mNickView.setText(mValues.get(position).getCard_Id());
        holder.mCategoryView.setText(mValues.get(position).getCard_Category());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final WebView mCardView;
        public final TextView mNickView;
        public final TextView mCategoryView;
        public CardItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNickView = (TextView) view.findViewById(R.id.nickname_txtB);
            mCategoryView = (TextView) view.findViewById(R.id.category_txtB);
            mCardView = (WebView) view.findViewById(R.id.faceimage);
        }

    }
}
