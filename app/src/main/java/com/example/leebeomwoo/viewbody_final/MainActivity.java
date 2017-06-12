package com.example.leebeomwoo.viewbody_final;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leebeomwoo.viewbody_final.Adapter.ListRecyclerViewAdapter;
import com.example.leebeomwoo.viewbody_final.Fragment.QnAFragment;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    SearchView mSearchView;
    MenuItem myActionMenuItem;
    TabLayout tabLayout;
    ViewPager viewPager;
    RelativeLayout maintab;
    Button back, menu;
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


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        ab.setCustomView(R.layout.toolbar);

        if(getIntent().hasExtra("message")) {
            Bundle bundle = getIntent().getExtras();
            i = bundle.getInt("message");
        }
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
                PopupWindow popupwindow_obj = popupDisplay();
                popupwindow_obj.showAsDropDown(menu, 1, 18);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ArrayList<MainTabItem> mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new MainTabItem("홈", null, Home_Tab.class));
        mainMenuItems.add(new MainTabItem("몸과 운동", null, BodyTab_Sub.class));
        mainMenuItems.add(new MainTabItem("음식과 영양", null, FoodTab_Sub.class));
        mainMenuItems.add(new MainTabItem("동영상 따라하기", null, FollowTab_Sub.class));
        mainMenuItems.add(new MainTabItem("트레이너와 영양사", null, WriterTab_Sub.class));
        mainMenuItems.add(new MainTabItem("묻고 답하기", null, QnAFragment.class));
        back = (Button) findViewById(R.id.tabbackBtn);
        menu = (Button) findViewById(R.id.tabmenuBtn);
        tabLayout = (TabLayout) findViewById(R.id.main_TabLayout);
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
    public PopupWindow popupDisplay()
    {
        final PopupWindow popupWindow = new PopupWindow(this);

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.menu, null);

        ImageButton cancel_menuBtn = (ImageButton) view.findViewById(R.id.cancel_menuBtn);
        ImageButton home_menuBtn = (ImageButton) view.findViewById(R.id.home_menuBtn);
        ImageButton indi_menuBtn = (ImageButton) view.findViewById(R.id.indi_menuBtn);
        ImageButton body_menuBtn = (ImageButton) view.findViewById(R.id.body_menuBtn);
        ImageButton food_menuBtn = (ImageButton) view.findViewById(R.id.food_menuBtn);
        ImageButton follow_menuBtn = (ImageButton) view.findViewById(R.id.follow_menuBtn);
        ImageButton writer_menuBtn = (ImageButton) view.findViewById(R.id.writer_menuBtn);
        ImageButton qna_menuBtn = (ImageButton) view.findViewById(R.id.qna_menuBtn);
        ImageButton account_menuBtn = (ImageButton) view.findViewById(R.id.account_menuBtn);
        ListView listView = (ListView) view.findViewById(R.id.list_menu);
        TextView editText = (TextView) view.findViewById(R.id.menu_testtxt);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(view);



        return popupWindow;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_menuBtn:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.account_menuBtn:
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.body_menuBtn:
                viewPager.setCurrentItem(3, true);
                //foodTab_sub.changePage(2);
                break;
            case R.id.follow_menuBtn:
                viewPager.setCurrentItem(4, true);
                //foodTab_sub.changePage(1);
                break;
            case R.id.food_menuBtn:
                break;
            case R.id.home_menuBtn:
                break;
            case R.id.qna_menuBtn:
                Intent qintent = new Intent(MainActivity.this, QnaActivity.class);
                startActivity(qintent);
                break;
            case R.id.writer_menuBtn:
                viewPager.setCurrentItem(4, true);
                // foodTab_sub.changePage(4);
                break;
            case R.id.body:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.food:
                viewPager.setCurrentItem(2, true);
                break;
                // foodTab_sub.changePage(4);
            case R.id.follow:
                viewPager.setCurrentItem(3, true);
                break;
                //foodTab_sub.changePage(2);
            case R.id.writer:
                viewPager.setCurrentItem(4, true);
                break;
                //foodTab_sub.changePage(1);
        }
    }
}
