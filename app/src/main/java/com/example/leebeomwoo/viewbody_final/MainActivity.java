package com.example.leebeomwoo.viewbody_final;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.leebeomwoo.viewbody_final.Fragment.MovieFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.SettingsFragment;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Support.SlidingTabLayout;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // Get the ActionBar here to configure the way it behaves.
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.arrows); // set a custom icon for the default home button
            ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
            ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
        }

        ArrayList<MainTabItem> mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new MainTabItem("몸과" + "\n" + "운동", null, BodyTab_Sub.class));
        mainMenuItems.add(new MainTabItem("음식과" + "\n" + "영 양", null, FoodTab_Sub.class));
        mainMenuItems.add(new MainTabItem("동영상" + "\n" + "따라하기", null, MovieFragment.class));
        mainMenuItems.add(new MainTabItem("트레이너" + "\n" + "와 영양사", null, FoodTab_Sub.class));

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.main_TabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), mainMenuItems));

        slidingTabLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);  // Always cast your custom Toolbar here, and set it as the ActionBar.
    }
}
