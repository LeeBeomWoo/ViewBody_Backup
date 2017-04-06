package com.example.leebeomwoo.viewbody_final.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.EcItem;
import com.example.leebeomwoo.viewbody_final.ItemViewActivity;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder> implements Filterable{

    List<EcItem> ecItems;
    Context eContext;
    private final List<EcItem> userList;
    private final List<EcItem> filteredUserList;
    private UserFilter userFilter;

    public ExerciseRecyclerViewAdapter(Context context, List<EcItem> ecItemList) {
        this.eContext = context;
        this.ecItems = ecItemList;

        this.userList = new ArrayList<>();
        this.filteredUserList = new ArrayList<>();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
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
                .inflate(R.layout.fragment_detail, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final EcItem ecItem = ecItems.get(position);
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.txtViewTitle.setText(ecItem.getEc_Title());
        viewHolder.txtViewContent.setText(ecItem.getEc_Content());
        viewHolder.txtViewCategory.setText(ecItem.getEc_Category());
        // viewHolder.imgViewIcon.loadUrl(ConAdapter.SERVER_URL + ecItem.getEc_ImageUrl()); 실제 구동
        //테스트
        viewHolder.imgViewIcon.loadUrl(ConAdapter.SERVER_URL + "data_image/" + ecItem.getEc_ConectCode());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ItemViewActivity.class);
                String viewurl = ConAdapter.SERVER_URL + "data_image/" + ecItem.getEc_ConectCode();
                Log.d("exrecycler", viewurl);
                String tr_id = ecItem.getEc_Id();
                int q = 2;
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
        return (null != ecItems ? ecItems.size() : 0);
    }

    public void setEcItems (List<EcItem> ecItemList) {
        ecItems.clear();
        this.ecItems = ecItemList;
    }

    @Override
    public Filter getFilter() {
        if(userFilter == null)
            userFilter = new UserFilter(this, userList);
        return userFilter;
    }

    private static class UserFilter extends Filter {

        ExerciseRecyclerViewAdapter adapter;

        private final List<EcItem> originalList;

        private final List<EcItem> filteredList;

        private UserFilter(ExerciseRecyclerViewAdapter adapter, List<EcItem> originalList) {
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

                for (final EcItem user : originalList) {
                    if (user.getEc_Title().contains(filterPattern)) {
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
            adapter.filteredUserList.addAll((ArrayList<EcItem>) results.values);
            adapter.notifyDataSetChanged();
        }
    }

}
