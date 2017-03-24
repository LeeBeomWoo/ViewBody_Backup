package com.example.leebeomwoo.viewbody_final.ItemGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.leebeomwoo.viewbody_final.R;


public class ItemFragment extends Fragment {

    private WebView webView;
    String tr_id;
    String imageUrl;
    // TODO: Rename parameter arguments, choose names that match
    // TODO: Rename and change types of parametersv

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tr_id = getArguments().getString("tr_Id");
            imageUrl = getArguments().getString("itemUrl");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_item, container, false);
        webView = (WebView) view.findViewById(R.id.item_Web);
        webView.loadUrl(imageUrl);
        return view;
    }

}
