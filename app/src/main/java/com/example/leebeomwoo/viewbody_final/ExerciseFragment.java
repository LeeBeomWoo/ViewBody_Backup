package com.example.leebeomwoo.viewbody_final;

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

import com.example.leebeomwoo.viewbody_final.Response.ResponseCec;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseFragment extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {

    private View view;
    private static RecyclerView rv;
    List<EcItem> ecItems;
    ResponseEc responseEc;

    ResponseCec responseCec;
    @SuppressLint("StaticFieldLeak")
    static ExerciseRecyclerViewAdapter exadapter;

    String TAG = "Exercise";

    public ExerciseFragment(){}
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
        databinding();

        return view;
    }


    public void databinding ()
    {
        Call<ResponseEc> call = ConAdapter.getInstance().getResult_Ec();
        call.enqueue(new Callback<ResponseEc>() {
            @Override
            public void onResponse(Call<ResponseEc> call, Response<ResponseEc> response) {
                responseEc = response.body();
                ecItems = responseEc.getEcItem();
                exadapter = new ExerciseRecyclerViewAdapter(getActivity(), ecItems);
                rv.setAdapter(exadapter);
                Log.d(TAG,"서버와의 연결이 잘됐어요~.");
            }
            @Override
            public void onFailure(Call<ResponseEc> call, Throwable t) {
                Log.d(TAG,t.getMessage());
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
                    if(exadapter != null)
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
                                exadapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            };
            thread_two.start();
    }


    public void datachanged(String category) {
        Call<ResponseCec> call = ConAdapter.getInstance().CATEGORY_EXERCISE(category);
        call.enqueue(new Callback<ResponseCec>() {
            @Override
            public void onResponse(Call<ResponseCec> call, Response<ResponseCec> response) {
                responseCec = response.body();
                Log.d("response changed", response.body().toString());
                ecItems = responseCec.getEcItem();
                exadapter.setEcItems(ecItems);
            }
            @Override
            public void onFailure(Call<ResponseCec> call, Throwable t) {
            }
        });
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
    public void onClick(View view) {
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
        exadapter.getFilter().filter(query);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                exadapter.notifyDataSetChanged();
            }
        });
        return false;
    }

    public static ExerciseFragment newInstance() {
        return new ExerciseFragment();
    }
}


//