package com.example.leebeomwoo.viewbody_final;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.leebeomwoo.viewbody_final.Item.FmItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFm;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Adapter.MovieRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener{
    ResponseFm responseFm;
    MovieRecyclerViewAdapter adapter;
    List<FmItem> fmItems;
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
        rv.setLayoutManager(llm);
        Call<ResponseFm> call = ConAdapter.getInstance().getResult_Fm();
        call.enqueue(new Callback<ResponseFm>() {
            @Override
            public void onResponse(Call<ResponseFm> call, Response<ResponseFm> response) {
                responseFm = response.body();
                Toast toast = Toast.makeText(getContext(), responseFm.getResult(), Toast.LENGTH_SHORT);
                toast.show();
                fmItems = responseFm.getFmItem();
                adapter = new MovieRecyclerViewAdapter(getActivity(), fmItems);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseFm> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(),"서버와의 연결이 안됬습니다.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement our filter logic
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        return false;
    }

    public static MovieFragment newInstance() {
    return new MovieFragment();
    }
}
