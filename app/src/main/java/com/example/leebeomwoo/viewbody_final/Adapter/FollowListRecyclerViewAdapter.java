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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.LikeItem;
import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
import com.example.leebeomwoo.viewbody_final.ItemViewActivity;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Response.ResponseLd;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowListRecyclerViewAdapter extends RecyclerView.Adapter<FollowListRecyclerViewAdapter.ViewHolder> implements Filterable{
    private List<ListDummyItem> ldItems = new ArrayList<>();
    private LikeItem lkItems;
    Context bContext;
    ResponseLd responseLd;
    private final static String TAG = "FollowViewAdapter";
    private final static String FURL = "<html><body><iframe width=\"1080\" height=\"720\" src=\"";
    private final static String BURL = "\" frameborder=\"0\" allowfullscreen></iframe></html></body>";
    private final static String CHANGE = "https://www.youtube.com/embed";
    private final List<ListDummyItem> filteredUserList;
    private UserFilter userFilter;
    private String callClass, URL, change;
    Intent intent;

    public FollowListRecyclerViewAdapter(Context context, List<ListDummyItem> ldItemList){
        this.ldItems = ldItemList;
        this.bContext = context;
        this.filteredUserList = new ArrayList<>();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mView;
        public final TextView txtViewTitle, txtViewId;
        public final WebView imgView;
        public final Button likebutton, followbuttonn;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mView = (CardView) itemLayoutView.findViewById(R.id.followCard);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.follow_Title);
            imgView = (WebView) itemLayoutView.findViewById(R.id.list_movie);
            txtViewId = (TextView) itemLayoutView.findViewById(R.id.follow_Id);
            likebutton = (Button) itemLayoutView.findViewById(R.id.follow_Like);
            followbuttonn = (Button) itemLayoutView.findViewById(R.id.follow_Btn);
            imgView.setFocusable(false);
            imgView.setWebViewClient(new WebViewClient());
            WebviewSet(imgView);

            if (Build.VERSION.SDK_INT >= 19) {
                imgView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
            else {
                imgView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }
    }

    private void WebviewSet (WebView view){
        WebSettings settings = view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_follow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final ListDummyItem ldItem = ldItems.get(position);
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.txtViewTitle.setText(ldItem.getLd_Title());
        viewHolder.txtViewId.setText(ldItem.getLd_Id());
        intent = new Intent(bContext, ItemViewActivity.class);
        if(ldItem.getLd_Video() != null) {
            change = ldItem.getLd_Video().replace("https://youtu.be", CHANGE);
            URL = FURL + change + BURL;
            Log.d(TAG, URL);
            viewHolder.imgView.loadData(URL, "text/html", "utf-8");
        }
        viewHolder.likebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //좋아요 클릭했을 시 계정이 있는 지 확인 후 계정별로 하나의 게시물에 한번만 좋아요가 눌려지게 하고 기존에 눌렀던 적이 있다면 해당 좋아요를 취소하는 걸로 코딩
                   Call<LikeItem> call = ConAdapter.getInstance().getResult_List("Like", ldItem.getLd_Num(), "UserId");
                   call.enqueue(new Callback<LikeItem>() {
                       @Override
                       public void onResponse(Call<LikeItem> call, Response<LikeItem> response) {
                           lkItems = response.body();
                           Log.d(TAG, "서버와의 연결이 잘됐어요~.");
                           Log.d("response", lkItems.toString());
                       }

                       @Override
                       public void onFailure(Call<LikeItem> call, Throwable t) {
                           Log.d(TAG, t.getMessage());
                       }
                   });
            }
        });
        viewHolder.followbuttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("itemUrl", ldItem.getLd_ImageUrl());
                intent.putExtra("tr_Id", ldItem.getLd_Id());
                intent.putExtra("section", ldItem.getLd_Section());
                intent.putExtra("page_num", ldItem.getLd_Num());
                intent.putExtra("fragment", 2);
                bContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {

        return (null != ldItems ? ldItems.size() : 0);
    }

    public void setLkItems(List<ListDummyItem> bdItems1) {
        ldItems.clear();
        this.ldItems = bdItems1;
    }
    // inner class to hold a reference to each item of RecyclerView
    @Override
    public Filter getFilter() {
        if(userFilter == null)
            userFilter = new UserFilter(this, ldItems);
        return userFilter;
    }


    private class UserFilter extends Filter {

        FollowListRecyclerViewAdapter adapter;

        private final List<ListDummyItem> originalList;

        private final List<ListDummyItem> filteredList;

        private UserFilter(FollowListRecyclerViewAdapter adapter, List<ListDummyItem> originalList) {
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

                for (final ListDummyItem user : originalList) {
                    if (user.getLd_Title().contains(filterPattern)) {
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
            adapter.filteredUserList.addAll((ArrayList<ListDummyItem>) results.values);
            adapter.notifyDataSetChanged();
        }
    }


}
