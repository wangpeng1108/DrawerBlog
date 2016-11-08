package com.wangpeng.blog.drawerblog.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by WP on 2016/6/9.
 */

public class BaseUtils {
    private Context context;
    public BaseUtils(){

    }
    public BaseUtils(Context context){
        this.context = context;
    }

    public void println(Object s){
        System.out.println(s);
    }

    public void toast(Object s){
        Toast.makeText(context,s.toString(),Toast.LENGTH_SHORT).show();
    }

}
