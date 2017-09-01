package com.example.leebeomwoo.viewbody_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.leebeomwoo.viewbody_final.Adapter.LicenseListAdapter;
import com.example.leebeomwoo.viewbody_final.Adapter.StableArrayAdapter;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;
import com.example.leebeomwoo.viewbody_final.Fragment.QnAFragment;
import com.example.leebeomwoo.viewbody_final.Item.LicenseItem;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Support.CenteringTabLayout;

import java.util.ArrayList;
import java.util.Arrays;

import cn.gavinliu.android.lib.scale.config.ScaleConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    SearchView mSearchView;
    MenuItem myActionMenuItem;
    CenteringTabLayout tabLayout;
    TextView license_source, license_Title;
    ViewPager viewPager;
    RelativeLayout maintab;
    PopupWindow mPopupWindow;
    Context context;
    ImageButton cancel_menuBtn, account_menuBtn, body_menuBtn, follow_menuBtn, food_menuBtn, qna_menuBtn, writer_menuBtn, licenseBtn, back, menu, menuHomeBtn;
    BodyTab_Sub bodyTab_sub;
    FollowTab_Sub followTab_sub;
    FoodTab_Sub foodTab_sub;
    WriterTab_Sub writerTab_sub;
    ListView menu_list;
    ScrollView menu_Scroll;
    LinearLayout btn_View, main, top;
    CheckBox checkedTextView;
    public Drawable selector;

    String[] body, follow, food, trainer, license;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScaleConfig.create(this,
                1080, // Design Width
                1920, // Design Height
                3,    // Design Density
                3,    // Design FontScale
                ScaleConfig.DIMENS_UNIT_DP);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        back = (ImageButton) findViewById(R.id.tabbackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FirstActivity.class);
                intent.putExtra("message", 1);
                startActivity(intent);
            }
        });
        menu = (ImageButton) findViewById(R.id.tabmenuBtn);
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
        tabLayout = (CenteringTabLayout) findViewById(R.id.main_TabLayout);
        tabLayout.setClickable(true);
        tabLayout.setSelectedTabIndicatorHeight(17);
        maintab = (RelativeLayout) findViewById(R.id.maintablayout);
        tabLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), mainMenuItems));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                tabColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabColor(i);
        tabLayout.setSelected(true);
        new Handler().postDelayed(
                new Runnable() {
                    @Override public void run() {
                        tabLayout.getTabAt(i).select();
                    }
                }, 100);
        viewPager.setCurrentItem(i, true);
    }
    private void tabColor(int i){
        switch (i){
            case 0:
                maintab.setBackgroundResource(R.color.newtoolbar);
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.newsubtabcolor));
                break;
            case 1:
                maintab.setBackgroundResource(R.color.body_toolbar);
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.bodysubtabcolor));
                break;
            case 2:
                maintab.setBackgroundResource(R.color.followtoolbar);
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.followsubtabcolor));
                break;
            case 3:
                maintab.setBackgroundResource(R.color.foodtoolbar);
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.foodsubtabcolor));
                break;
            case 4:
                maintab.setBackgroundResource(R.color.writertoolbar);
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.writersubtabcolor));
                break;
            case 5:
                maintab.setBackgroundResource(R.color.qnatoolbar);
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.qnasubtabcolor));
                break;
        }
    }
    public PopupWindow popupDisplay(View v)
    {
        final View popupView = getLayoutInflater().inflate(R.layout.menu, null);
        /**
         * LayoutParams WRAP_CONTENT를 주면 inflate된 View의 사이즈 만큼의
         * PopupWinidow를 생성한다.
         *
         mPopupWindow = new PopupWindow(popupView,
         RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
         */
        mPopupWindow = new PopupWindow(popupView,
                900, RelativeLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAsDropDown(v, 0, 0);
        mPopupWindow.setOutsideTouchable(true);
        cancel_menuBtn = popupView.findViewById(R.id.cancel_menuBtn);
        account_menuBtn = popupView.findViewById(R.id.account_menuBtn);
        body_menuBtn = popupView.findViewById(R.id.body_menuBtn);
        follow_menuBtn = popupView.findViewById(R.id.follow_menuBtn);
        food_menuBtn = popupView.findViewById(R.id.food_menuBtn);
        qna_menuBtn = popupView.findViewById(R.id.qna_Btn);
        writer_menuBtn =  popupView.findViewById(R.id.writer_menuBtn);
        menu_list = popupView.findViewById(R.id.menu_list);
        menu_Scroll = popupView.findViewById(R.id.menu_Scroll);
        btn_View = popupView.findViewById(R.id.btn_View);
        main = popupView.findViewById(R.id.menu_main);
        top = popupView.findViewById(R.id.menu_top);
        menuHomeBtn = popupView.findViewById(R.id.menuBtn_home);
        //licenseBtn = popupView.findViewById(R.id.license_Btn);
        license_source = popupView.findViewById(R.id.sourceTxt);
        license_Title = popupView.findViewById(R.id.titleTxt);
        checkedTextView = popupView.findViewById(R.id.menuchecked);

        cancel_menuBtn.setOnClickListener(this);
        account_menuBtn.setOnClickListener(this);
        body_menuBtn.setOnClickListener(this);
        follow_menuBtn.setOnClickListener(this);
        food_menuBtn.setOnClickListener(this);
        qna_menuBtn.setOnClickListener(this);
        writer_menuBtn.setOnClickListener(this);
       // licenseBtn.setOnClickListener(this);
        checkedTextView.setOnClickListener(this);
        SharedPreferences preferencesCompat = getSharedPreferences("a", MODE_PRIVATE);
        int tutorial = preferencesCompat.getInt("First", 0);
        if(tutorial == 0){
            checkedTextView.setChecked(true);
        } else {
            checkedTextView.setChecked(false);
        }
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
                R.layout.menulistitem, list);
        menu_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //foodTab_sub.changePage(2);
    }
    private void license_listSet(){
        final ArrayList<LicenseItem> list = new ArrayList<LicenseItem>();
        String[] title = {"Square", "Retrofit2"};
        String[] source = {"Copyright 2016 Square, Inc.\n" + "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" + "\n" +
                "http://www.apache.org/licenses/LICENSE-2.0\n" + "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.",
                "Copyright 2013 Square, Inc.\n" + "\n" + "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        "you may not use this file except in compliance with the License.\n" +
                        " You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0\n"+ "\n" +
                        "Unless required by applicable law or agreed to in writing, software\n" +
                        "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        "See the License for the specific language governing permissions and\n" +
                        "limitations under the License."};
        for (int i = 0; i < title.length; ++i) {
            list.add(new LicenseItem(title[i], source[i]));
        }
        final LicenseListAdapter adapter = new LicenseListAdapter(this,
                R.layout.license, list);
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
        license = new String[]{};
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
                body_menuBtn.setImageResource(R.drawable.menu_body_sel);
                follow_menuBtn.setImageResource(R.drawable.menu_follow);
                food_menuBtn.setImageResource(R.drawable.menu_food_sel);
                writer_menuBtn.setImageResource(R.drawable.menu_writer);
                body_menuBtn.setBackgroundResource(R.color.menubackcolor);
                follow_menuBtn.setBackgroundResource(R.color.nocolor);
                food_menuBtn.setBackgroundResource(R.color.nocolor);
                writer_menuBtn.setBackgroundResource(R.color.nocolor);
               // licenseBtn.setBackgroundResource(R.color.nocolor);
                break;
            case R.id.follow_menuBtn:
                viewPager.setCurrentItem(2, true);
                menu_listSet(follow);
                follow_menuBtn.setImageResource(R.drawable.menu_follow_sel);
                body_menuBtn.setImageResource(R.drawable.menu_body);
                food_menuBtn.setImageResource(R.drawable.menu_food);
                writer_menuBtn.setImageResource(R.drawable.menu_writer);
                follow_menuBtn.setBackgroundResource(R.color.menubackcolor);
                body_menuBtn.setBackgroundResource(R.color.nocolor);
                food_menuBtn.setBackgroundResource(R.color.nocolor);
                writer_menuBtn.setBackgroundResource(R.color.nocolor);
               // licenseBtn.setBackgroundResource(R.color.nocolor);
                //foodTab_sub.changePage(1);
                break;
            case R.id.food_menuBtn:
                viewPager.setCurrentItem(3, true);
                menu_listSet(food);
                food_menuBtn.setImageResource(R.drawable.menu_food_sel);
                body_menuBtn.setImageResource(R.drawable.menu_body);
                follow_menuBtn.setImageResource(R.drawable.menu_follow);
                writer_menuBtn.setImageResource(R.drawable.menu_writer);
                food_menuBtn.setBackgroundResource(R.color.menubackcolor);
                body_menuBtn.setBackgroundResource(R.color.nocolor);
                follow_menuBtn.setBackgroundResource(R.color.nocolor);
                writer_menuBtn.setBackgroundResource(R.color.nocolor);
               // licenseBtn.setBackgroundResource(R.color.nocolor);
                break;
            case R.id.qna_Btn:
                Intent qintent = new Intent(MainActivity.this, QnaActivity.class);
                btn_View.setBackgroundResource(R.color.qnatoolbar);
                startActivity(qintent);
                break;
            case R.id.writer_menuBtn:
                viewPager.setCurrentItem(4, true);
                menu_listSet(trainer);
                writer_menuBtn.setImageResource(R.drawable.menu_writer_sel);
                body_menuBtn.setImageResource(R.drawable.menu_body);
                follow_menuBtn.setImageResource(R.drawable.menu_follow);
                food_menuBtn.setImageResource(R.drawable.menu_food);
                writer_menuBtn.setBackgroundResource(R.color.menubackcolor);
                body_menuBtn.setBackgroundResource(R.color.nocolor);
                follow_menuBtn.setBackgroundResource(R.color.nocolor);
                food_menuBtn.setBackgroundResource(R.color.nocolor);
                //licenseBtn.setBackgroundResource(R.color.nocolor);
                // foodTab_sub.changePage(4);
                break;
            /**
            case R.id.license_Btn:
                license_listSet();
                licenseBtn.setBackgroundResource(R.color.menubackcolor);
                body_menuBtn.setBackgroundResource(R.color.nocolor);
                follow_menuBtn.setBackgroundResource(R.color.nocolor);
                food_menuBtn.setBackgroundResource(R.color.nocolor);
                writer_menuBtn.setBackgroundResource(R.color.nocolor);
                break;*/
            case R.id.menuchecked:
                SharedPreferences pref = getSharedPreferences("a", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if(checkedTextView.isChecked()){
                    editor.putInt("First", 0);
                } else{
                    editor.putInt("First", 1);
                }
                editor.apply();
                break;
            case R.id.menuBtn_home:
                Intent home = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(home);
        }
    }
}
/** 상체 : 1, 하체 : 2
 * 상관 없음 : 0, 전면 : 1, 후면 : 2
 * 상관 없음 : 0, 골격 : 1, 근육 : 2, 근력 : 3, 근지구력 : 4, 근파워 : 5, 심폐지구력 : 6, 유연성 : 7
 * 상관 없음 : 0, 상완 : 1, 하완 : 2,  어깨 : 3, 목 : 4, 허벅지 : 5, 종아리 : 6, 가슴 : 7, 엉덩이 : 8, 복부 : 9
 */