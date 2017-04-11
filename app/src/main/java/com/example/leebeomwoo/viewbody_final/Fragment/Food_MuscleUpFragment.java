package com.example.leebeomwoo.viewbody_final.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leebeomwoo.viewbody_final.Adapter.ListRecyclerViewAdapter;
import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCbd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseLd;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Food_MuscleUpFragment extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView rv;
    ResponseFd responseFd;
    ResponseCbd responseCbd;

    private List<ListDummyItem> ldItems;
    @SuppressLint("StaticFieldLeak")
    static ListRecyclerViewAdapter bdadapter;

    String TAG = "BodyFragment";
    public Food_MuscleUpFragment(){}

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_list, container, false);
        rv = (RecyclerView) view.findViewById(R.id.detail_list);
        setHasOptionsMenu(true);
        rv.setHasFixedSize(true);
        getActivity().invalidateOptionsMenu();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        Call<ResponseFd> call = ConAdapter.getInstance().getResult_MuscleUp();
        call.enqueue(new Callback<ResponseFd>() {
            @Override
            public void onResponse(Call<ResponseFd> call, Response<ResponseFd> response) {
                responseFd = response.body();
                Log.d(TAG,"서버와의 연결이 잘됐어요~.");
                ldItems = responseFd.getFdItem();
                Log.d("response", ldItems.toString());
            }
            @Override
            public void onFailure(Call<ResponseFd> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
        bdadapter = new ListRecyclerViewAdapter(getActivity(), ldItems);
        rv.setAdapter(bdadapter);

        return view;
    }

    public void datachanged(String category) {
        Call<ResponseCbd> call = ConAdapter.getInstance().CATEGORY_BODY(category);
        call.enqueue(new Callback<ResponseCbd>() {
            @Override
            public void onResponse(Call<ResponseCbd> call, Response<ResponseCbd> response) {
                responseCbd = response.body();
                Log.d("response changed", response.body().toString());
                ldItems = responseCbd.getCbdItem();
                bdadapter.setLdItems(ldItems);
            }
            @Override
            public void onFailure(Call<ResponseCbd> call, Throwable t) {
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
            final boolean keepRunning1 = true;
            Thread thread_two = new Thread() {
                @Override
                public void run() {
                    if(bdadapter != null) {
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
                                    bdadapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                }
            };
            thread_two.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach()");
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
        bdadapter.getFilter().filter(query);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bdadapter.notifyDataSetChanged();
            }
        });
        return false;
    }


    public static Food_MuscleUpFragment newInstance() {
        return new Food_MuscleUpFragment();
    }
}
