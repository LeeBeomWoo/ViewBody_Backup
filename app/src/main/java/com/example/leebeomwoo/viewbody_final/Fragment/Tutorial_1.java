package com.example.leebeomwoo.viewbody_final.Fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Lee on 2017-07-04.
 */

public class Tutorial_1  extends Fragment
{
    public Tutorial_1()
    {
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_third, container, false);
        return layout;
    }
}


출처: http://itpangpang.xyz/284 [ITPangPang]
