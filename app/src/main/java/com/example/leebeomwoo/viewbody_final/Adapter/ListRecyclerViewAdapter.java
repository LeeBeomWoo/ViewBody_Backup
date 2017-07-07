package com.example.leebeomwoo.viewbody_final.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Item.LikeItem;
import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
import com.example.leebeomwoo.viewbody_final.ItemViewActivity;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Response.ResponseLd;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Support.HelpWebView;
import com.example.leebeomwoo.viewbody_final.Support.RecyclerviewClickEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder> implements Filterable {
    private List<ListDummyItem> ldItems = new ArrayList<>();
    private LikeItem lkItems;
    Drawable drawable;
    Context bContext;
    ResponseLd responseLd;
    private final static String TAG = "ListRecyclerViewAdapter";
    private final static String FURL = "<html><body><iframe width=\"1080\" height=\"720\" src=\"";
    private final static String BURL = "\" frameborder=\"0\" allowfullscreen></iframe></html></body>";
    private final static String CHANGE = "https://www.youtube.com/embed";
    private final List<ListDummyItem> filteredUserList;
    private UserFilter userFilter;
    private String callClass, URL1, URL2, URL3, change;
    RecyclerviewClickEvent clickEvent = new RecyclerviewClickEvent();
    Intent intent;
    public ListRecyclerViewAdapter(Context context, List<ListDummyItem> ldItemList){
        this.ldItems = ldItemList;
        this.bContext = context;
        this.filteredUserList = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {
        public final CardView mView;
        public final TextView txtViewTitle, txtViewId, video_title_1, video_title_2, video_title_3;
        public final WebView imgViewFace, videoView_1, videoView_2, videoView_3;
        public final Button button;
        public final HelpWebView imgViewIcon;
        public final ImageView categoryImage;


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
            categoryImage = (ImageView) itemLayoutView.findViewById(R.id.itemtitle_image);
            imgViewFace.setWebViewClient(new WebViewClient());
            imgViewIcon.setWebViewClient(new WebViewClient());
            imgViewFace.setFocusable(false);
            imgViewIcon.setFocusable(false);
            imgViewFace.setFocusable(false);
            imgViewIcon.setFocusable(false);
            WebviewSet(imgViewFace);
            WebviewSet(imgViewIcon);
            videoView_1.setFocusable(false);
            videoView_1.setWebViewClient(new WebViewClient());
            WebviewSet(videoView_1);
            videoView_2.setFocusable(false);
            videoView_2.setWebViewClient(new WebViewClient());
            WebviewSet(videoView_2);
            videoView_3.setFocusable(false);
            videoView_3.setWebViewClient(new WebViewClient());
            WebviewSet(videoView_3);
            /**
             * webview.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
             장점 이건 설정하면 웹뷰를 계속 띄워도 뻗지 않는다 .
             페이지 전환시 껌뻑임이 없다
             단점은 웹뷰 내용을 스크롤 할 때 느리게 스크롤 되는 단점이있다
             나는 계속 보니 어지러워서 토나올것 같더라

             webview.setLayerType(View.LAYER_TYPE_HARDWARE,null);
             장점은 스크롤이 괭장히 부드럽게 된다 .
             단점은 웹뷰 한 .. 10개 정도 .. 액티비티당 2개씩이라서 한 5개 정도 띄우면 뻗는다 버퍼에러 난다
             그리고 페이지 불러올때 껌뻑인다 .. 퍼즐 맞추듯이 맞춰 진다
             이거 할때는 android:hardwareAccelerated="true" 이것도 메니페스트 <application에 추가하자

             출처: http://writefoot.tistory.com/entry/웹뷰-껌뻑임-현상 [발로쓰는 블로그]
             */
            if (Build.VERSION.SDK_INT >= 21) {
                imgViewIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                imgViewFace.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
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
            imgViewIcon.setOnTouchListener(this);
            imgViewFace.setOnClickListener(this);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("onClick", view.toString());
            if(view.getId() == button.getId()){
                Call<LikeItem> call = ConAdapter.getInstance().getResult_List("Like", getItem(getLayoutPosition()).getLd_Num(), "UserId");
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
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (view.getId() == imgViewIcon.getId()) {
                    clickEvent.Click(ldItems.get(getLayoutPosition()), bContext);
                } else if (view.getId() == imgViewFace.getId()) {
                    intent.putExtra("itemUrl", "trainer");
                    intent.putExtra("tr_Id", getItem(getLayoutPosition()).getLd_Id());
                    intent.putExtra("section", getItem(getLayoutPosition()).getLd_Section());
                    //intent_2.putExtra("item_word", item_word);
                    intent.putExtra("fragment", 1);
                    bContext.startActivity(intent);
                }
                return true;
            }
            return false;
        }
    }
    private void WebviewSet(WebView view){
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
        viewHolder.categoryImage.setImageDrawable(titlecategory(ldItem.getLd_Category()));
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

    }
    private void titlecategory(){
        titlecategory(0);
    }
    private Drawable titlecategory(int i){
        switch (i){
            case 0://근육
                drawable = bContext.getResources().getDrawable(R.drawable.logomain);
                break;
            case 1://골격
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 2://근지구력
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 3://근파워
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 4://머슬업
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 5://허리
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 6://상완
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 7://하완
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 8://복부
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 9://가슴
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 10://어깨
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 11://목
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 12://허벅지
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 13://종아리
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 14://엉덩이
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 15://상체
                drawable = bContext.getResources().getDrawable(R.drawable.upper);
            break;
            case 16://하체
                drawable = bContext.getResources().getDrawable(R.drawable.lower);
            break;
            case 17://몸통
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 18://심폐지구력
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
            case 19://미정
                drawable = bContext.getResources().getDrawable(R.drawable.foodlogo);
            break;
        }
        return drawable;
    }
    public ListDummyItem getItem(int position){
        return ldItems.get(position);
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
