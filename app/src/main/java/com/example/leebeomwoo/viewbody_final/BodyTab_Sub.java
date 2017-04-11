package com.example.leebeomwoo.viewbody_final;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leebeomwoo.viewbody_final.Fragment.Lower_BoneFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Lower_MuscleFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Upper_BoneFragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Upper_MuscleFragment;
import com.example.leebeomwoo.viewbody_final.Item.CardItem;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCard;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Support.SlidingTabLayout;
import com.example.leebeomwoo.viewbody_final.Adapter.MyCardRecyclerViewAdapter;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class BodyTab_Sub extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "0";
    View view;
    private ArrayList<MainTabItem> items;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BodyTab_Sub() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Id Parameter 1.
     * @param section Parameter 2.
     * @return A new instance of fragment BodyTab_Sub.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment BodyTab_Sub_start(String Id, String section) {
        BodyTab_Sub fragment = new BodyTab_Sub();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Id);
        args.putString(ARG_PARAM2, section);
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
            view = inflater.inflate(R.layout.fragment_body_tab__sub, container, false);

            items = new ArrayList<>();
            items.add(new MainTabItem("상체" + "\n" + "근육", mParam1, Upper_BoneFragment.class));
            items.add(new MainTabItem("상체" + "\n" + "골격", mParam1, Upper_MuscleFragment.class));
            items.add(new MainTabItem("하체" + "\n" + "근육", mParam1, Lower_BoneFragment.class));
            items.add(new MainTabItem("하체" + "\n" + "골격", mParam1, Lower_MuscleFragment.class));
            SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.body_TabLayout);
            ViewPager viewPager = (ViewPager) view.findViewById(R.id.body_viewPager);

            viewPager.setAdapter(new TabsAdapter(getChildFragmentManager(), items));
            slidingTabLayout.setViewPager(viewPager);
        return view;
    }

}
