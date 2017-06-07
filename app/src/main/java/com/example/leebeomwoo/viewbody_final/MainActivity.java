package com.example.leebeomwoo.viewbody_final;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.leebeomwoo.viewbody_final.Fragment.QnAFragment;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    SearchView mSearchView;
    MenuItem myActionMenuItem;
    TabLayout tabLayout;
    ViewPager viewPager;
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
        if(getIntent().hasExtra("message")) {
            Bundle bundle = getIntent().getExtras();
            i = bundle.getInt("message");
        }
        // Get access to the custom title view
        ImageView mTitle = (ImageView) findViewById(R.id.toolbar_title);
        ImageView mToolImage = (ImageView) findViewById(R.id.toolbar_image);
        // Display icon in the toolbar
        // toolbar.inflateMenu(R.menu.menu_main);
        // mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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

        tabLayout = (TabLayout) findViewById(R.id.main_TabLayout);
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), mainMenuItems));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(i, true);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.body:
                viewPager.setCurrentItem(1, true);
                return true;
            case R.id.food:
                viewPager.setCurrentItem(2, true);
                // foodTab_sub.changePage(4);
                return true;
            case R.id.follow:
                viewPager.setCurrentItem(3, true);
                //foodTab_sub.changePage(2);
                return true;
            case R.id.writer:
                viewPager.setCurrentItem(4, true);
                //foodTab_sub.changePage(1);
                return true;
            case R.id.account:
                Intent intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                return true;
            case R.id.qna:
                Intent qintent = new Intent(this, QnaActivity.class);
                startActivity(qintent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
