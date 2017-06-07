package com.example.leebeomwoo.viewbody_final.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.LikeItem;
import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
import com.example.leebeomwoo.viewbody_final.ItemViewActivity;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Response.ResponseLd;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Support.HelpWebView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder> implements Filterable{
    private List<ListDummyItem> ldItems = new ArrayList<>();
    private LikeItem lkItems;
    Context bContext;
    ResponseLd responseLd;
    private final static String TAG = "ListRecyclerViewAdapter";
    private final static String FURL = "<html><body><iframe width=\"1080\" height=\"720\" src=\"";
    private final static String BURL = "\" frameborder=\"0\" allowfullscreen></iframe></html></body>";
    private final static String CHANGE = "https://www.youtube.com/embed";
    private final List<ListDummyItem> filteredUserList;
    private UserFilter userFilter;
    private String callClass, URL1, URL2, URL3, change;
    Intent intent;
    public ListRecyclerViewAdapter(Context context, List<ListDummyItem> ldItemList){
        this.ldItems = ldItemList;
        this.bContext = context;
        this.filteredUserList = new ArrayList<>();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mView;
        public final TextView txtViewTitle, txtViewId, video_title_1, video_title_2, video_title_3;
        public final HelpWebView imgViewIcon;
        public final WebView imgViewFace, videoView_1, videoView_2, videoView_3;
        public final Button button;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mView = (CardView) itemLayoutView.findViewById(R.id.cardView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.detile_Title);
            imgViewIcon = (HelpWebView) itemLayoutView.findViewById(R.id.detile_Image);
            video_title_1 = (TextView) itemLayoutView.findViewById(R.id.video_title_1);
            video_title_2 = (TextView) itemLayoutView.findViewById(R.id.video_title_2);
            video_title_3 = (TextView) itemLayoutView.findViewById(R.id.video_title_3);
            imgViewFace = (WebView) itemLayoutView.findViewById(R.id.detile_face);
            videoView_1 = (WebView) itemLayoutView.findViewById(R.id.video_view_1);
            videoView_2 = (WebView) itemLayoutView.findViewById(R.id.video_view_2);
            txtViewId = (TextView) itemLayoutView.findViewById(R.id.detile_Id);
            button = (Button) itemLayoutView.findViewById(R.id.like_btn);
            videoView_3 = (WebView) itemLayoutView.findViewById(R.id.video_view_3);
            imgViewIcon.setFocusable(false);
            imgViewIcon.getSettings().setJavaScriptEnabled(true);
            imgViewIcon.getSettings().setDomStorageEnabled(true);
            imgViewIcon.getSettings().setUseWideViewPort(true);
            imgViewIcon.getSettings().setLoadWithOverviewMode(true);
            imgViewFace.setFocusable(false);
            imgViewFace.getSettings().setJavaScriptEnabled(true);
            imgViewFace.getSettings().setDomStorageEnabled(true);
            imgViewFace.getSettings().setUseWideViewPort(true);
            imgViewFace.getSettings().setLoadWithOverviewMode(true);
            videoView_1.setFocusable(false);
            videoView_1.setWebViewClient(new WebViewClient());
            WebviewSet(videoView_1);
            videoView_2.setFocusable(false);
            videoView_2.setWebViewClient(new WebViewClient());
            WebviewSet(videoView_2);
            videoView_3.setFocusable(false);
            videoView_3.setWebViewClient(new WebViewClient());
            WebviewSet(videoView_3);

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
                .inflate(R.layout.fragment_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final ListDummyItem ldItem = ldItems.get(position);
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.txtViewTitle.setText(ldItem.getLd_Title());
        viewHolder.imgViewIcon.loadUrl(ConAdapter.SERVER_URL + ldItem.getLd_ImageUrl());
        viewHolder.txtViewId.setText(ldItem.getLd_Id());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(bContext);
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
                videoView_1.setFocusable(false);
                videoView_1.setWebViewClient(new WebViewClient());
                WebviewSet(videoView_1);
                videoView_2.setFocusable(false);
                videoView_2.setWebViewClient(new WebViewClient());
                WebviewSet(videoView_2);
                videoView_3.setFocusable(false);
                videoView_3.setWebViewClient(new WebViewClient());
                WebviewSet(videoView_3);
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
                if(ldItem.getLd_Video() != null) {
                    String[] animalsArray = ldItem.getLd_Video().split(",");
                    for (int i = 0; i < animalsArray.length; i++) {
                        Log.d(TAG + "animalsArray_" + i, animalsArray[i]);
                    }
                    switch (animalsArray.length) {
                        case 2:
                            video_title_1.setVisibility(View.VISIBLE);
                            video_title_1.setText(animalsArray[0]);
                            change = animalsArray[1].replace("https://youtu.be", CHANGE);
                            URL1 = FURL + change + BURL;
                            Log.d(TAG, URL1);
                            videoView_1.setVisibility(View.VISIBLE);
                            videoView_1.loadData(URL1, "text/html", "utf-8");
                            break;
                        case 4:
                            video_title_1.setVisibility(View.VISIBLE);
                            video_title_1.setText(animalsArray[0]);
                            change = animalsArray[1].replace("https://youtu.be", CHANGE);
                            URL1 = FURL + change + BURL;
                            Log.d(TAG, URL1);
                            videoView_1.setVisibility(View.VISIBLE);
                            videoView_1.loadData(URL1, "text/html", "utf-8");

                            video_title_2.setVisibility(View.VISIBLE);
                            video_title_2.setText(animalsArray[2]);
                            change = animalsArray[3].replace("https://youtu.be", CHANGE);
                            URL2 = FURL + change + BURL;
                            Log.d(TAG, URL2);
                            videoView_2.setVisibility(View.VISIBLE);
                            videoView_2.loadData(URL2, "text/html", "utf-8");
                            break;
                        case 6:
                            video_title_1.setVisibility(View.VISIBLE);
                            video_title_1.setText(animalsArray[0]);
                            change = animalsArray[1].replace("https://youtu.be", CHANGE);
                            URL1 = FURL + change + BURL;
                            Log.d(TAG, URL1);
                            videoView_1.setVisibility(View.VISIBLE);
                            videoView_1.loadData(URL1, "text/html", "utf-8");

                            video_title_2.setVisibility(View.VISIBLE);
                            video_title_2.setText(animalsArray[2]);
                            change = animalsArray[3].replace("https://youtu.be", CHANGE);
                            URL2 = FURL + change + BURL;
                            Log.d(TAG, URL2);
                            videoView_2.setVisibility(View.VISIBLE);
                            videoView_2.loadData(URL2, "text/html", "utf-8");

                            video_title_3.setVisibility(View.VISIBLE);
                            video_title_3.setText(animalsArray[4]);
                            change = animalsArray[5].replace("https://youtu.be", CHANGE);
                            URL3 = FURL + change + BURL;
                            Log.d(TAG, URL3);
                            videoView_3.setVisibility(View.VISIBLE);
                            videoView_3.loadData(URL3, "text/html", "utf-8");
                            break;
                    }
                }
                dialog.show();
            }
        });
        intent = new Intent(bContext, ItemViewActivity.class);
        if(ldItem.getLd_Video() != null) {
            String[] animalsArray = ldItem.getLd_Video().split(",");
            for(int i = 0; i < animalsArray.length; i++) {
                Log.d(TAG + "animalsArray_" + i, animalsArray[i]);
            }
            switch (animalsArray.length){
                case 2:
                    viewHolder.video_title_1.setVisibility(View.VISIBLE);
                    viewHolder.video_title_1.setText(animalsArray[0]);
                    change = animalsArray[1].replace("https://youtu.be", CHANGE);
                    URL1 = FURL + change + BURL;
                    Log.d(TAG, URL1);
                    viewHolder.videoView_1.setVisibility(View.VISIBLE);
                    viewHolder.videoView_1.loadData(URL1, "text/html", "utf-8");
                    break;
                case 4:
                    viewHolder.video_title_1.setVisibility(View.VISIBLE);
                    viewHolder.video_title_1.setText(animalsArray[0]);
                    change = animalsArray[1].replace("https://youtu.be", CHANGE);
                    URL1 = FURL + change + BURL;
                    Log.d(TAG, URL1);
                    viewHolder.videoView_1.setVisibility(View.VISIBLE);
                    viewHolder.videoView_1.loadData(URL1, "text/html", "utf-8");

                    viewHolder.video_title_2.setVisibility(View.VISIBLE);
                    viewHolder.video_title_2.setText(animalsArray[2]);
                    change = animalsArray[3].replace("https://youtu.be", CHANGE);
                    URL2 = FURL + change + BURL;
                    Log.d(TAG, URL2);
                    viewHolder.videoView_2.setVisibility(View.VISIBLE);
                    viewHolder.videoView_2.loadData(URL2, "text/html", "utf-8");
                    break;
                case 6:
                    viewHolder.video_title_1.setVisibility(View.VISIBLE);
                    viewHolder.video_title_1.setText(animalsArray[0]);
                    change = animalsArray[1].replace("https://youtu.be", CHANGE);
                    URL1 = FURL + change + BURL;
                    Log.d(TAG, URL1);
                    viewHolder.videoView_1.setVisibility(View.VISIBLE);
                    viewHolder.videoView_1.loadData(URL1, "text/html", "utf-8");

                    viewHolder.video_title_2.setVisibility(View.VISIBLE);
                    viewHolder.video_title_2.setText(animalsArray[2]);
                    change = animalsArray[3].replace("https://youtu.be", CHANGE);
                    URL2 = FURL + change + BURL;
                    Log.d(TAG, URL2);
                    viewHolder.videoView_2.setVisibility(View.VISIBLE);
                    viewHolder.videoView_2.loadData(URL2, "text/html", "utf-8");

                    viewHolder.video_title_3.setVisibility(View.VISIBLE);
                    viewHolder.video_title_3.setText(animalsArray[4]);
                    change = animalsArray[5].replace("https://youtu.be", CHANGE);
                    URL3 = FURL + change + BURL;
                    Log.d(TAG, URL3);
                    viewHolder.videoView_3.setVisibility(View.VISIBLE);
                    viewHolder.videoView_3.loadData(URL3, "text/html", "utf-8");
                    break;
            }
        }
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
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
        viewHolder.imgViewFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("itemUrl", "trainer");
                intent.putExtra("tr_Id", ldItem.getLd_Id());
                intent.putExtra("section", ldItem.getLd_Section());
                //intent_2.putExtra("item_word", item_word);
                intent.putExtra("fragment", 1);
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

        ListRecyclerViewAdapter adapter;

        private final List<ListDummyItem> originalList;

        private final List<ListDummyItem> filteredList;

        private UserFilter(ListRecyclerViewAdapter adapter, List<ListDummyItem> originalList) {
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
