package com.example.leebeomwoo.viewbody_final;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;
import com.example.leebeomwoo.viewbody_final.Fragment.QnAFragment;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Support.CenteringTabLayout;
import com.example.leebeomwoo.viewbody_final.Support.SlidingTabLayout;
import com.example.leebeomwoo.viewbody_final.Support.SlidingTabStrip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    SearchView mSearchView;
    MenuItem myActionMenuItem;
    CenteringTabLayout tabLayout;
    ViewPager viewPager;
    RelativeLayout maintab;
    PopupWindow mPopupWindow;
    Button back, menu;
    Context context;
    ImageButton cancel_menuBtn, account_menuBtn, body_menuBtn, follow_menuBtn, food_menuBtn, home_menuBtn, qna_menuBtn, writer_menuBtn;
    BodyTab_Sub bodyTab_sub;
    FollowTab_Sub followTab_sub;
    FoodTab_Sub foodTab_sub;
    WriterTab_Sub writerTab_sub;
    ListView menu_list;

    String[] body, follow, food, trainer;

    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        ab.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        ab.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.

        if(getIntent().hasExtra("message")) {
            Bundle bundle = getIntent().getExtras();
            i = bundle.getInt("message");
        }
        context = this;
        // Get access to the custom title view
        // Display icon in the toolbar
        // toolbar.inflateMenu(R.menu.menu_main);
        // mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        back = (Button) findViewById(R.id.tabbackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        menu = (Button) findViewById(R.id.tabmenuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDisplay(v);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        ArrayList<MainTabItem> mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new MainTabItem("홈", null, Home_Tab.class));
        mainMenuItems.add(new MainTabItem("몸과 운동", null, BodyTab_Sub.class));
        mainMenuItems.add(new MainTabItem("동영상 따라하기", null, FollowTab_Sub.class));
        mainMenuItems.add(new MainTabItem("음식과 영양", null, FoodTab_Sub.class));
        mainMenuItems.add(new MainTabItem("트레이너와 영양사", null, WriterTab_Sub.class));
        mainMenuItems.add(new MainTabItem("묻고 답하기", null, QnAFragment.class));
        back = (Button) findViewById(R.id.tabbackBtn);
        menu = (Button) findViewById(R.id.tabmenuBtn);
        tabLayout = (CenteringTabLayout) findViewById(R.id.main_TabLayout);
        tabLayout.setClickable(false);
        maintab = (RelativeLayout) findViewById(R.id.maintablayout);
        tabLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), mainMenuItems));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d("Scrolled position :", String.valueOf(position));
                Log.d("positionOffset :", String.valueOf(positionOffset));
                Log.d("positionOffsetPixels :", String.valueOf(positionOffsetPixels));
            }

            @Override
            public void onPageSelected(int position) {
                tabColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabColor(i);
        viewPager.setCurrentItem(i, true);
    }
    private void tabColor(int i){
        switch (i){
            case 0:
                maintab.setBackgroundResource(R.color.newtoolbar);
                break;
            case 1:
                maintab.setBackgroundResource(R.color.body_toolbar);
                break;
            case 2:
                maintab.setBackgroundResource(R.color.foodtoolbar);
                break;
            case 3:
                maintab.setBackgroundResource(R.color.followtoolbar);
                break;
            case 4:
                maintab.setBackgroundResource(R.color.writertoolbar);
                break;
            case 5:
                maintab.setBackgroundResource(R.color.qnatoolbar);
                break;
        }
    }

    public PopupWindow popupDisplay(View v)
    {
        final View popupView = getLayoutInflater().inflate(R.layout.menu, null);
        /**
         * LayoutParams WRAP_CONTENT를 주면 inflate된 View의 사이즈 만큼의
         * PopupWinidow를 생성한다.
         */
        mPopupWindow = new PopupWindow(popupView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAsDropDown(v, 0, 0);
        cancel_menuBtn = (ImageButton) popupView.findViewById(R.id.cancel_menuBtn);
        account_menuBtn = (ImageButton) popupView.findViewById(R.id.account_menuBtn);
        body_menuBtn = (ImageButton) popupView.findViewById(R.id.body_menuBtn);
        follow_menuBtn = (ImageButton) popupView.findViewById(R.id.follow_menuBtn);
        food_menuBtn = (ImageButton) popupView.findViewById(R.id.food_menuBtn);
        home_menuBtn = (ImageButton) popupView.findViewById(R.id.home_menuBtn);
        qna_menuBtn = (ImageButton) popupView.findViewById(R.id.qna_menuBtn);
        writer_menuBtn = (ImageButton) popupView.findViewById(R.id.writer_menuBtn);
        menu_list = (ListView) popupView.findViewById(R.id.menu_list);
        cancel_menuBtn.setOnClickListener(this);
        account_menuBtn.setOnClickListener(this);
        body_menuBtn.setOnClickListener(this);
        follow_menuBtn.setOnClickListener(this);
        food_menuBtn.setOnClickListener(this);
        home_menuBtn.setOnClickListener(this);
        qna_menuBtn.setOnClickListener(this);
        writer_menuBtn.setOnClickListener(this);

        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
               switch (item){
                   case "상체 운동":
                       bodyTab_sub = new BodyTab_Sub();
                       bodyTab_sub.setTabitemSelected(0);
                       mPopupWindow.dismiss();
                       break;
                   case "상체 정보":
                       bodyTab_sub = new BodyTab_Sub();
                       bodyTab_sub.setTabitemSelected(1);
                       mPopupWindow.dismiss();
                       break;
                   case "하체 운동":
                       bodyTab_sub = new BodyTab_Sub();
                       bodyTab_sub.setTabitemSelected(2);
                       mPopupWindow.dismiss();
                       break;
                   case "하체 정보":
                       bodyTab_sub = new BodyTab_Sub();
                       bodyTab_sub.setTabitemSelected(3);
                       mPopupWindow.dismiss();
                       break;
                   case "스트레칭":
                       bodyTab_sub = new BodyTab_Sub();
                       bodyTab_sub.setTabitemSelected(4);
                       mPopupWindow.dismiss();
                       break;
                   case "코어 운동":
                       followTab_sub = new FollowTab_Sub();
                       followTab_sub.setTabitemSelected(0);
                       mPopupWindow.dismiss();
                       break;
                   case "유산소운동":
                       followTab_sub = new FollowTab_Sub();
                       followTab_sub.setTabitemSelected(1);
                       mPopupWindow.dismiss();
                       break;
                   case "근력운동":
                       followTab_sub = new FollowTab_Sub();
                       followTab_sub.setTabitemSelected(2);
                       mPopupWindow.dismiss();
                       break;
                   case "스트레칭 따라하기":
                       followTab_sub = new FollowTab_Sub();
                       followTab_sub.setTabitemSelected(3);
                       mPopupWindow.dismiss();
                       break;
                   case "체지방감소":
                       foodTab_sub = new FoodTab_Sub();
                       foodTab_sub.setTabitemSelected(0);
                       mPopupWindow.dismiss();
                       break;
                   case "근력강화":
                       foodTab_sub = new FoodTab_Sub();
                       foodTab_sub.setTabitemSelected(1);
                       mPopupWindow.dismiss();
                       break;
                   case "근육량증대":
                       foodTab_sub = new FoodTab_Sub();
                       foodTab_sub.setTabitemSelected(2);
                       mPopupWindow.dismiss();
                       break;
                   case "몸매관리":
                       foodTab_sub = new FoodTab_Sub();
                       foodTab_sub.setTabitemSelected(3);
                       mPopupWindow.dismiss();
                       break;
                   case "대사증후군":
                       foodTab_sub = new FoodTab_Sub();
                       foodTab_sub.setTabitemSelected(4);
                       mPopupWindow.dismiss();
                       break;
                   case "트레이너":
                       writerTab_sub = new WriterTab_Sub();
                       writerTab_sub.setTabitemSelected(0);
                       mPopupWindow.dismiss();
                       break;
                   case "영양사":
                       writerTab_sub = new WriterTab_Sub();
                       writerTab_sub.setTabitemSelected(1);
                       mPopupWindow.dismiss();
                       break;

               }
            }

        });
        /**
         * @View anchor : anchor View를 기준으로 바로 아래 왼쪽에 표시.
         * @예외 : 하지만 anchor View가 화면에 가장 하단 View라면 시스템이
         * 자동으로 위쪽으로 표시되게 한다.
         * xoff, yoff : anchor View를 기준으로 PopupWindow가 xoff는 x좌표,
         * yoff는 y좌표 만큼 이동된 위치에 표시되게 한다.
         * @int xoff : -숫자(화면 왼쪽으로 이동), +숫자(화면 오른쪽으로 이동)
         * @int yoff : -숫자(화면 위쪽으로 이동), +숫자(화면 아래쪽으로 이동)
         * achor View 를 덮는 것도 가능.
         * 화면바깥 좌우, 위아래로 이동 가능. (짤린 상태로 표시됨)
         * mPopupWindow.showAsDropDown(btn_Popup, 50, 50);
         */
            mPopupWindow.setAnimationStyle(-1); // 애니메이션 설정(-1:설정, 0:설정안함)

        /**
         * showAtLocation(parent, gravity, x, y)
         * @praent : PopupWindow가 생성될 parent View 지정
         * View v = (View) findViewById(R.id.btn_click)의 형태로 parent 생성
         * @gravity : parent View의 Gravity 속성 지정 Popupwindow 위치에 영향을 줌.
         * @x : PopupWindow를 (-x, +x) 만큼 좌,우 이동된 위치에 생성
         * @y : PopupWindow를 (-y, +y) 만큼 상,하 이동된 위치에 생성
         * mPopupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 0, 0);
         * */
         mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        /**
         * update() 메서드를 통해 PopupWindow의 좌우 사이즈, x좌표, y좌표
         * anchor View까지 재설정 해줄수 있습니다.
         * mPopupWindow.update(anchor, xoff, yoff, width, height)(width, height);
         */
        return mPopupWindow;
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main, menu);
        myActionMenuItem = menu.findItem( R.id.action_search);
        mSearchView = (SearchView) myActionMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                //UserFeedback.show( "SearchOnQueryTextSubmit: " + query);
                if( ! mSearchView.isIconified()) {
                    mSearchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }
private void menu_listSet(String[] values){
    final ArrayList<String> list = new ArrayList<String>();
    //Log.d("menu_listSet list:", ArrayList.(list));
    Log.d("menu_listSet values:", Arrays.toString(values));
    for (int i = 0; i < values.length; ++i) {
        list.add(values[i]);
        Log.d("menu_listSet list:", values[i]);
    }
    final StableArrayAdapter adapter = new StableArrayAdapter(this,
            android.R.layout.simple_list_item_1, list);
    menu_list.setAdapter(adapter);
    adapter.notifyDataSetChanged();
    //foodTab_sub.changePage(2);
}
    @Override
    public void onClick(View v) {

        body = new String[]{"상체 운동", "상체 정보", "하체 운동", "하체 정보","스트레칭"};
        follow = new String[] {"코어 운동", "유산소운동", "근력운동", "스트레칭 따라하기"};
        food = new String[]{"체지방감소", "근력강화", "근육량증대", "몸매관리", "대사증후군"};
        trainer = new String[]{"트레이너", "영양사"};
        Log.d("main :", Arrays.toString(trainer) + Arrays.toString(body));
        switch (v.getId()) {
            case R.id.cancel_menuBtn:
                mPopupWindow.dismiss();
                break;
            case R.id.account_menuBtn:
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.body_menuBtn:
                viewPager.setCurrentItem(1, true);
                menu_listSet(body);
                break;
            case R.id.follow_menuBtn:
                viewPager.setCurrentItem(2, true);
                menu_listSet(follow);
                //foodTab_sub.changePage(1);
                break;
            case R.id.food_menuBtn:
                viewPager.setCurrentItem(3, true);
                menu_listSet(food);
                break;
            case R.id.home_menuBtn:
                break;
            case R.id.qna_menuBtn:
                Intent qintent = new Intent(MainActivity.this, QnaActivity.class);
                startActivity(qintent);
                break;
            case R.id.writer_menuBtn:
                viewPager.setCurrentItem(4, true);
                menu_listSet(trainer);
                // foodTab_sub.changePage(4);
                break;

        }
    }
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
