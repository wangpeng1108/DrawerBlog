package com.wangpeng.blog.drawerblog.beans;

/**
 * Created by WP on 2016/5/31.
 */

public class Blog {
    private String data;
    private String id;
    private String title;
    private String click;
    private String reply;
    private String desc;
    private String pic;

    public Blog(){}

    public Blog(String data, String id, String title, String click, String reply, String desc,String pic) {
        this.data = data;
        this.id = id;
        this.title = title;
        this.click = click;
        this.reply = reply;
        this.desc = desc;
        this.pic = pic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
