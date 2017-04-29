package com.example.leebeomwoo.viewbody_final.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.leebeomwoo.viewbody_final.Item.LikeItem;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFm;
import com.example.leebeomwoo.viewbody_final.Adapter.MovieRecyclerViewAdapter;

import java.util.List;

public class MovieFragment extends android.support.v4.app.Fragment {
    ResponseFm responseFm;
    MovieRecyclerViewAdapter adapter;
    List<LikeItem> likeItems;
    private View view;
    public MovieFragment(){}
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_recyclelist, container, false);
        final RecyclerView rv = (RecyclerView)view.findViewById(R.id.movie_list);
        setHasOptionsMenu(true);
        rv.setHasFixedSize(true);
        getActivity().invalidateOptionsMenu();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);/**
        Call<ResponseFm> call = ConAdapter.getInstance().getResult_List("LS");
        call.enqueue(new Callback<ResponseFm>() {
            @Override
            public void onResponse(Call<ResponseFm> call, Response<ResponseFm> response) {
                responseFm = response.body();
                Toast toast = Toast.makeText(getContext(), responseFm.getResult(), Toast.LENGTH_SHORT);
                toast.show();
                likeItems = responseFm.getLikeItem();
                adapter = new MovieRecyclerViewAdapter(getActivity(), likeItems);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseFm> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(),"서버와의 연결이 안됬습니다.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });**/
        return view;
    }


    public static MovieFragment newInstance() {
    return new MovieFragment();
    }
}
