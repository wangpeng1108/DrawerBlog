package com.wangpeng.blog.drawerblog.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangpeng.blog.drawerblog.R;

/**
 * Created by WP on 2016/6/12.
 */

public class DetailFrame extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.androidfragment, container, false);
        initView(rootView);

        //initJson();
        return rootView;
    }

    private void initView(View rootView) {
    }
}
