package com.example.leebeomwoo.viewbody_final.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.leebeomwoo.viewbody_final.Item.UserInformationItem;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Support.ConAdapter;
import com.example.leebeomwoo.viewbody_final.Support.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeeBeomWoo on 2017-04-27.
 */

public class Join_Fragment extends Fragment {
    Button join, out;
    EditText id, pw, pwck, weight, tall, hope;
    String TAG = "Join_Fragment";
    UserInformationItem item;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.join_page, container, false);
        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();
        id = (EditText) view.findViewById(R.id.id_join_txtB);
        pw = (EditText) view.findViewById(R.id.pw_join_txtB);
        pwck = (EditText) view.findViewById(R.id.pwck_join_txtB);
        weight = (EditText) view.findViewById(R.id.weight_join_txtB);
        tall = (EditText) view.findViewById(R.id.tall_join_txtB);
        hope = (EditText) view.findViewById(R.id.hope_join_txtB);
        join = (Button) view.findViewById(R.id.join_ok_btn);
        out = (Button) view.findViewById(R.id.join_cancel_btn);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    //해당 내용을 서버로 전달
                if(pw.getText() == pwck.getText()){
                    item.setId(id.getText().toString());
                    item.setPassWord(pw.getText().toString());
                    item.setWeight(weight.getText().toString());
                    item.setTall(tall.getText().toString());
                    item.setHope(hope.getText().toString());
                    Call<UserInformationItem> call = ConAdapter.getInstance().joinpush(item);
                    call.enqueue(new Callback<UserInformationItem>(){
                        @Override
                        public void onResponse(Call<UserInformationItem> call, Response<UserInformationItem> response) {
                            item = response.body();
                        }

                        @Override
                        public void onFailure(Call<UserInformationItem> call, Throwable t) {
                            Log.d(TAG, t.toString());
                        }
                    });
                }
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // account로 돌아가기
                Fragment fragment = new Account_Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.account_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
