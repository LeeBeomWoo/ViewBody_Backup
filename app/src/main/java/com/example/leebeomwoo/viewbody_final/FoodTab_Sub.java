package com.example.leebeomwoo.viewbody_final;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leebeomwoo.viewbody_final.Fragment.Food_DietFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Food_FatFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Food_MetabolicFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Food_MuscleUpFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Food_PowerUpFragment;
import com.example.leebeomwoo.viewbody_final.Item.CardItem;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCard;
import com.example.leebeomwoo.viewbody_final.Support.SlidingTabLayout;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class FoodTab_Sub extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "1";
    View view;
    private ArrayList<MainTabItem> items;
    private List<CardItem> cardItem;
    private ResponseCard responseCard;

    // TODO: Rename and change types of parameters
    private String mParam1, mParam2;

    public FoodTab_Sub() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Id Parameter 1.
     * @param OnOff Parameter 2.
     * @return A new instance of fragment FoodTab_Sub.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment FoodTab_Sub_start(String Id, String OnOff) {
        FoodTab_Sub fragment = new FoodTab_Sub();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Id);
        args.putString(ARG_PARAM2, OnOff);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_food_tab_sub, container, false);

            items = new ArrayList<>();
            items.add(new MainTabItem("체지방" + "\n" + "감 소", mParam1, Food_FatFragment.class));
            items.add(new MainTabItem("근력강화", mParam1, Food_PowerUpFragment.class));
            items.add(new MainTabItem("근육량" + "\n" + "증 대", mParam1, Food_MuscleUpFragment.class));
            items.add(new MainTabItem("몸매관리", mParam1, Food_DietFragment.class));
            items.add(new MainTabItem("대 사" + "\n" + "증후군", mParam1, Food_MetabolicFragment.class));
            SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.food_TabLayout);
            ViewPager viewPager = (ViewPager) view.findViewById(R.id.food_viewPager);

            viewPager.setAdapter(new TabsAdapter(getChildFragmentManager(), items));
            slidingTabLayout.setViewPager(viewPager);
        return view;
    }

}
