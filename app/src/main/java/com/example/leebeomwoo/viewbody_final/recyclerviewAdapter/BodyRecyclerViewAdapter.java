package com.example.leebeomwoo.viewbody_final.recyclerviewAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.BdItem;
import com.example.leebeomwoo.viewbody_final.ItemViewActivity;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BodyRecyclerViewAdapter extends RecyclerView.Adapter<BodyRecyclerViewAdapter.ViewHolder> implements Filterable{
    List<BdItem> bdItems = new ArrayList<>();
    Context bContext;
    private final List<BdItem> filteredUserList;
    private UserFilter userFilter;



    public BodyRecyclerViewAdapter(Context context, List<BdItem> bdItemList){
        this.bdItems = bdItemList;
        this.bContext = context;
        this.filteredUserList = new ArrayList<>();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mView;
        public final TextView txtViewTitle;
        public final TextView txtViewContent;
        public final TextView txtViewCategory;
        public final WebView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mView = (CardView) itemLayoutView.findViewById(R.id.cardView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.detile_Title);
            txtViewContent = (TextView) itemLayoutView.findViewById(R.id.detile_Content);
            txtViewCategory = (TextView) itemLayoutView.findViewById(R.id.detile_category);
            imgViewIcon = (WebView) itemLayoutView.findViewById(R.id.detile_Image);
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
        final BdItem bdItem = bdItems.get(position);
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.txtViewTitle.setText(bdItem.getBd_Title());
        viewHolder.txtViewContent.setText(bdItem.getBd_Content());
        viewHolder.txtViewCategory.setText(bdItem.getBd_Category());
       //  viewHolder.imgViewIcon.loadUrl(ConAdapter.SERVER_URL + bdItem.getBd_ImageUrl()); 실제 구동시
        viewHolder.imgViewIcon.loadUrl(ConAdapter.SERVER_URL + "data_image/" + bdItem.getBd_ConectCode());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ItemViewActivity.class);
                String viewurl = ConAdapter.SERVER_URL + "data_image/" + bdItem.getBd_ConectCode();
                String tr_id = bdItem.getBd_Id();
                int q =1;
                //intent.putExtra("item_word", item_word);
                intent.putExtra("itemUrl", viewurl);
                intent.putExtra("trId", tr_id);
                intent.putExtra("page_num", q);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {

        return (null != bdItems ? bdItems.size() : 0);
    }

    public void setBdItems (List<BdItem> bdItems1) {
        bdItems.clear();
        this.bdItems = bdItems1;
    }
    // inner class to hold a reference to each item of RecyclerView
    @Override
    public Filter getFilter() {
        if(userFilter == null)
            userFilter = new UserFilter(this, bdItems);
        return userFilter;
    }


    private class UserFilter extends Filter {

        BodyRecyclerViewAdapter adapter;

        private final List<BdItem> originalList;

        private final List<BdItem> filteredList;

        private UserFilter(BodyRecyclerViewAdapter adapter, List<BdItem> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new LinkedList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for (final BdItem user : originalList) {
                    if (user.getBd_Title().contains(filterPattern)) {
                        filteredList.add(user);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filteredUserList.clear();
            adapter.filteredUserList.addAll((ArrayList<BdItem>) results.values);
            adapter.notifyDataSetChanged();
        }
    }


}
