package com.example.leebeomwoo.viewbody_final;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leebeomwoo.viewbody_final.Item.BdItem;
import com.example.leebeomwoo.viewbody_final.extra.SlidingTabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<BdItem> mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new BdItem("몸과 운동", BodyTab_Sub.class));
        mainMenuItems.add(new BdItem("음식과 영양", FoodTab_Sub.class));

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.main_TabLayout);
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), mainMenuItems));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }
}
