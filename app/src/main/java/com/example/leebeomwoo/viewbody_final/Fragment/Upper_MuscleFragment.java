package com.example.leebeomwoo.viewbody_final.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leebeomwoo.viewbody_final.Adapter.ListRecyclerViewAdapter;
import com.example.leebeomwoo.viewbody_final.Item.LikeItem;
import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCbd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseLd;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Support.RecyclerItemClickListener;
import com.example.leebeomwoo.viewbody_final.Support.RecyclerviewClickEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upper_MuscleFragment extends android.support.v4.app.Fragment {

    private RecyclerView rv;
    ResponseLd responseLd;
    ResponseCbd responseCbd;
    private LikeItem lkItems;

    private List<ListDummyItem> ldItems;
    @SuppressLint("StaticFieldLeak")
    ListRecyclerViewAdapter bdadapter;
    RecyclerviewClickEvent clickEvent = new RecyclerviewClickEvent();
    String TAG = "Upper_MuscleFragment";
    Intent intent;
    public Upper_MuscleFragment(){}

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
        rv.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("onItemClick" , view.toString());
                switch (view.getId()){
                case R.id.detile_Image:
                    clickEvent.Click(bdadapter.getItem(position), getActivity());
                    break;
                case R.id.detile_face:
                    intent.putExtra("itemUrl", "trainer");
                    intent.putExtra("tr_Id", bdadapter.getItem(position).getLd_Id());
                    intent.putExtra("section", bdadapter.getItem(position).getLd_Section());
                    //intent_2.putExtra("item_word", item_word);
                    intent.putExtra("fragment", 1);
                    getActivity().startActivity(intent);
                    break;
                case R.id.like_btn:
                    Call<LikeItem> call = ConAdapter.getInstance().getResult_List("Like", bdadapter.getItem(position).getLd_Num(), "UserId");
                    call.enqueue(new Callback<LikeItem>() {
                        @Override
                        public void onResponse(Call<LikeItem> call, Response<LikeItem> response) {
                            lkItems = response.body();
                            Log.d(TAG, "서버와의 연결이 잘됐어요~.");
                            Log.d("response", lkItems.toString());
                        }

                        @Override
                        public void onFailure(Call<LikeItem> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                        }
                    });
                    break;
            }
            }
        }));
        Call<ResponseLd> call = ConAdapter.getInstance().getResult_Ld("Upper_Muscle");
        call.enqueue(new Callback<ResponseLd>() {
            @Override
            public void onResponse(Call<ResponseLd> call, Response<ResponseLd> response) {
                responseLd = response.body();
                Log.d(TAG,"서버와의 연결이 잘됐어요~.");
                ldItems = responseLd.getLdItem();
                Log.d("response", ldItems.toString());
                bdadapter = new ListRecyclerViewAdapter(getActivity(), ldItems);
                rv.setAdapter(bdadapter);
            }
            @Override
            public void onFailure(Call<ResponseLd> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
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
                bdadapter.setLkItems(ldItems);
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

    public static Upper_MuscleFragment newInstance() {
        return new Upper_MuscleFragment();
    }
}
