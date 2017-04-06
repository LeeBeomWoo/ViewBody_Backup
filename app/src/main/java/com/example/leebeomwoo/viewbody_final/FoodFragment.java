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

import com.example.leebeomwoo.viewbody_final.Item.FdItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFd;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Adapter.FoodRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener{
    ResponseFd responseFd;
    List<FdItem> fdItems;
    private View view;
    RecyclerView rv;
    static FoodRecyclerViewAdapter adapter;
    public FoodFragment(){}

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_list, container, false);
        rv = (RecyclerView) view.findViewById(R.id.detail_list);
        setHasOptionsMenu(true);
        rv.setHasFixedSize(true);
        getActivity().invalidateOptionsMenu();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        Call<ResponseFd> call = ConAdapter.getInstance().getResult_Fd();
        call.enqueue(new Callback<ResponseFd>() {
            @Override
            public void onResponse(Call<ResponseFd> call, Response<ResponseFd> response) {
                responseFd = response.body();
                Toast toast = Toast.makeText(getContext(),responseFd.getResult(), Toast.LENGTH_SHORT);
                toast.show();
                fdItems = responseFd.getFdItem();
                adapter = new FoodRecyclerViewAdapter(getContext(), fdItems);
                rv.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<ResponseFd> call, Throwable t) {
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
    public void onResume(){
        super.onResume();
        final boolean keepRunning1 = true;
        Thread thread_two = new Thread() {
            @Override
            public void run() {
                if(adapter != null) {
                    while (keepRunning1) {

                        // Make the thread wait half a second. If you want...
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Toast.makeText(getActivity().getApplicationContext(), "Default Signature                         Fail", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                        // here you check the value of getActivity() and break up if needed
                        if (getActivity() == null)
                            return;

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        };
        thread_two.start();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        return false;
    }

    public static FoodFragment newInstance() {
    return new FoodFragment();
    }
}
