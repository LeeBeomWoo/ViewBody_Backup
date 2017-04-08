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

import com.example.leebeomwoo.viewbody_final.Item.QItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseQ;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Adapter.MyQnARecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class QnAFragment extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener{

    ResponseQ responseQ;
    MyQnARecyclerViewAdapter adapter;
    List<QItem> qItems;
    private View view;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qna_list, container, false);
        final RecyclerView rv = (RecyclerView)view.findViewById(R.id.qna_list);
        setHasOptionsMenu(true);
        rv.setHasFixedSize(true);
        getActivity().invalidateOptionsMenu();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        Call<ResponseQ> call = ConAdapter.getInstance().getResult_Q();
        call.enqueue(new Callback<ResponseQ>() {
            @Override
            public void onResponse(Call<ResponseQ> call, Response<ResponseQ> response) {
                responseQ = response.body();
                Toast toast = Toast.makeText(getContext(), responseQ.getResult(), Toast.LENGTH_SHORT);
                toast.show();
                qItems = responseQ.getqItem();
                adapter = new MyQnARecyclerViewAdapter(getActivity(), qItems);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseQ> call, Throwable t) {
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

    public static QnAFragment newInstance() {
        return new QnAFragment();
    }
}
