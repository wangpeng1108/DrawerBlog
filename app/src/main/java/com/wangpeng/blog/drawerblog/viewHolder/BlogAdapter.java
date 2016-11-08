package com.wangpeng.blog.drawerblog.viewHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.apache.http.Header;

import com.loopj.android.http.BinaryHttpResponseHandler;
import com.wangpeng.blog.drawerblog.R;
import com.wangpeng.blog.drawerblog.beans.Blog;
import com.wangpeng.blog.drawerblog.utils.BaseUtils;
import com.wangpeng.blog.drawerblog.utils.HttpUtils;



/**
 * 适配器
 * 
 *
 */
public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
	private List<Blog> list;
	private Bitmap bitmap;

	public BlogAdapter(List<Blog> list) {
		this.list = list;

	}

	@Override
	public int getItemCount() {
		return list.isEmpty() ? 0 : list.size();
	}

	@SuppressLint("InflateParams")
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
		ViewHolder vh = new ViewHolder(view);

		vh.pic = (ImageView) view.findViewById(R.id.pic);
		vh.title = (TextView) view.findViewById(R.id.title);
		vh.desc = (TextView) view.findViewById(R.id.desc);
		vh.data = (TextView) view.findViewById(R.id.date);
		vh.reply = (TextView) view.findViewById(R.id.reply);
		vh.click = (TextView) view.findViewById(R.id.click);

		return vh;
	}

	/** 
	* 设置值 
	*/
	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		final Blog rc = list.get(i);
		Bitmap b = getImage(rc.getPic());
		if(b!=null)
		viewHolder.pic.setImageBitmap(b);

		viewHolder.title.setText(rc.getTitle());
		viewHolder.desc.setText(rc.getDesc());
		viewHolder.data.setText(rc.getData());
		viewHolder.reply.setText(rc.getReply());
		viewHolder.click.setText(rc.getClick());
		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null) {
			viewHolder.title.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mOnItemClickLitener.onItemClick(viewHolder.title, i);
				}
			});
			viewHolder.desc.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mOnItemClickLitener.onItemClick(viewHolder.desc, i);
				}
			});
			viewHolder.pic.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mOnItemClickLitener.onItemClick(viewHolder.pic, i);
				}
			});
		}
	}

	static class ViewHolder extends RecyclerView.ViewHolder {

		private ImageView pic;
		private TextView title, desc,data,reply,click;

		public ViewHolder(View itemView) {
			super(itemView);
		}
	}

	public Bitmap getImage(String url) {
		new BaseUtils().println("图片地址："+url);
		HttpUtils.get(HttpUtils.getAbsoluteUrl(url), new BinaryHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] b) {
				//super.onSuccess(arg0);
				if(statusCode==200){
					bitmap =  BitmapFactory.decodeByteArray(b, 0, b.length);
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {

			}
		});
		return bitmap;
	}
	/**
	 * ItemClick的回调接口
	 */
	public interface OnItemClickLitener {
		void onItemClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
		this.mOnItemClickLitener = mOnItemClickLitener;
	}


}