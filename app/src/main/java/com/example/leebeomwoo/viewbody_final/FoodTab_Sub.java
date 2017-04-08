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

import com.example.leebeomwoo.viewbody_final.Item.CardItem;
import com.example.leebeomwoo.viewbody_final.Item.MainTabItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCard;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Support.SlidingTabLayout;
import com.example.leebeomwoo.viewbody_final.Adapter.MyCardRecyclerViewAdapter;
import com.example.leebeomwoo.viewbody_final.Adapter.TabsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FoodTab_Sub#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodTab_Sub extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "1";
    View view;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private ArrayList<MainTabItem> items;
    private List<CardItem> cardItem;
    private ResponseCard responseCard;

    // TODO: Rename and change types of parameters
    private String mParam1, mParam2;

    public FoodTab_Sub() {
        // Required empty public constructor
        items = new ArrayList<>();
        items.add(new MainTabItem("체지방" + "\n" + "감 소", mParam1, FoodFragment.class));
        items.add(new MainTabItem("근력강화", mParam1, FoodFragment.class));
        items.add(new MainTabItem("근육량" + "\n" + "증 대", mParam1, FoodFragment.class));
        items.add(new MainTabItem("몸매관리", mParam1, FoodFragment.class));
        items.add(new MainTabItem("대 사" + "\n" + "증후군", mParam1, FoodFragment.class));
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
        if (mParam2 == "1") {
            view = inflater.inflate(R.layout.fragment_food_tab__sub, container, false);

            slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.food_TabLayout);
            viewPager = (ViewPager) view.findViewById(R.id.food_viewPager);

            viewPager.setAdapter(new TabsAdapter(getChildFragmentManager(), items));
            slidingTabLayout.setViewPager(viewPager);
        } else {
            view = inflater.inflate(R.layout.fragment_card_list, container, false);
            RecyclerView rv = (RecyclerView) view.findViewById(R.id.card_list);
            setHasOptionsMenu(true);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            rv.setAdapter(new MyCardRecyclerViewAdapter(getActivity(), databinding()));
        }
        return view;
    }

    public List<CardItem> databinding ()
    {
        Call<ResponseCard> call = ConAdapter.getInstance().getResult_Card();
        call.enqueue(new Callback<ResponseCard>() {
            @Override
            public void onResponse(Call<ResponseCard> call, Response<ResponseCard> response) {
                responseCard = response.body();
                Log.d(TAG,"서버와의 연결이 잘됐어요~.");
                cardItem = responseCard.getfCardItem();
                Log.d("food :", cardItem.toString());
            }
            @Override
            public void onFailure(Call<ResponseCard> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
        return cardItem;
    }
}
