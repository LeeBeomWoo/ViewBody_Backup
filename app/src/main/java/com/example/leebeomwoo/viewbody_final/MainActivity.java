package com.example.leebeomwoo.viewbody_final;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.leebeomwoo.viewbody_final.Fragment.MovieFragment;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Support.SlidingTabLayout;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<MainTabItem> mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new MainTabItem("몸과 운동", null, BodyTab_Sub.class));
        mainMenuItems.add(new MainTabItem("음식과 영양", null, FoodTab_Sub.class));
        mainMenuItems.add(new MainTabItem("동영상" + "\n" + "따라하기", null, MovieFragment.class));
        mainMenuItems.add(new MainTabItem("트레이너와" + "\n" + "영양사", null, FoodTab_Sub.class));

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.main_TabLayout);
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), mainMenuItems));
        slidingTabLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }
}
