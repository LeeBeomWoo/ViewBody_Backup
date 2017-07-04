package com.example.leebeomwoo.viewbody_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageButton;

public class IntroActivity extends AppCompatActivity {
    ImageButton next, back;
    ViewPager viewPager;
    CheckBox checkedTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        next = (ImageButton) findViewById(R.id.next_tutorial_Btn);
        back = (ImageButton) findViewById(R.id.before_tutorial_Btn);
        viewPager = (ViewPager) findViewById(R.id.tutorial);
        checkedTextView = (CheckBox) findViewById(R.id.checkedTextView);

        viewPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        next.setImageResource(R.drawable.arrows);
                        back.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        next.setImageResource(R.drawable.arrows);
                        back.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        next.setImageResource(R.drawable.arrows);
                        back.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        next.setImageResource(R.drawable.close);
                        back.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager.getCurrentItem() != 3) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                } else {
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager.getCurrentItem() != 0)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("a", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if(checkedTextView.isChecked()){
                    editor.putInt("First", 1);
                } else{
                    editor.putInt("First", 0);
                }
                editor.apply();
            }
        });
    }


    private class PagerAdapterClass extends PagerAdapter{

        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            int page;
            switch (position){
                case 0:
                    v = mInflater.inflate(R.layout.tutorial_1, null);
                    break;
                case 1:
                    v = mInflater.inflate(R.layout.tutorial_2, null);
                    break;
                case 2:
                    v = mInflater.inflate(R.layout.tutorial_3, null);
                    break;
                case 3:
                    v = mInflater.inflate(R.layout.tutorial_4, null);
                    break;
            }
            if(position==0){
                page = position;
            }
            else if(position==1){
                page = position;
            }else{
                page = position;
            }
            ((ViewPager)pager).addView(v, 0);
            return v;
        }

        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager)pager).removeView((View)view);
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }
        @Override public void startUpdate(View arg0) {}
        @Override public void finishUpdate(View arg0) {

        }


    }

}
