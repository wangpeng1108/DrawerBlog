package com.wangpeng.blog.drawerblog.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.wangpeng.blog.drawerblog.R;
import com.wangpeng.blog.drawerblog.beans.Blog;
import com.wangpeng.blog.drawerblog.utils.BaseUtils;
import com.wangpeng.blog.drawerblog.utils.HttpUtils;
import com.wangpeng.blog.drawerblog.viewHolder.BlogAdapter;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WP on 2016/5/28.
 */

public class AndroidFrame extends Fragment {
    private android.support.v7.widget.RecyclerView recyclerView;
    private BlogAdapter adapter;
    private LinearLayoutManager manager;
    private List<Blog> list=new ArrayList<>();
    private String text;

    public AndroidFrame(){

    }

    public void setText(String text){
        this.text=text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.androidfragment, container, false);
        initView(rootView);

        initJson();
        return rootView;
    }
    /**
     * 初始化控件
     */
    private void initView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.arecycleview);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 1.获取图片的数据(已完成)

        // 2.设置布局管理器
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        // 3.设置适配器
        adapter = new BlogAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickLitener(new BlogAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + ","+list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initJson() {
        // 访问服务器端 获取json数据
        // 创建客户端对象
        final AsyncHttpClient client = HttpUtils.getClient();
        String u = getIds();
        //Toast.makeText(getActivity(),u,Toast.LENGTH_SHORT).show();
        String url = HttpUtils.getAbsoluteUrl("index.json"+u);
        System.out.println(url);
        BaseUtils base = new BaseUtils();
        //Toast.makeText(getActivity(), "正在从服务器获取数据..", Toast.LENGTH_LONG).show();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                new BaseUtils().println(response);
                try {
                    JSONArray array = response.getJSONArray("blogList");
                    Blog b;
                    for (int i = 0; i < array.length(); i++) {
                    b = new Blog();
                    // 获取具体的一个JSONObject对象
                    JSONObject obj = array.getJSONObject(i);
                    b.setId(obj.getString("id"));
                    b.setTitle(obj.getString("title"));
                    b.setPic(obj.getString("pic"));
                    b.setClick(obj.getString("click"));
                    b.setData(obj.getString("data"));
                    b.setDesc(obj.getString("desc"));
                    b.setReply(obj.getString("reply"));
                    list.add(b);
                }
            }catch (Exception e){
                        new BaseUtils().println(e.getMessage());
                    }
                    System.out.println("获取完成，共"+list.size()+"条");
                    initData();

                this.onFinish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,JSONObject object) {
                super.onFailure(statusCode, headers, throwable, object);
                //new BaseUtils(getActivity()).toast("获取数据出错");
                this.onCancel();
                this.onFinish();
            }
        });

    }

    private String getIds(){
        StringBuilder sb = new StringBuilder("?typeId=");
        if(text==null||text.equals(""))return "";
        return sb.append(text).toString();
    }

}
