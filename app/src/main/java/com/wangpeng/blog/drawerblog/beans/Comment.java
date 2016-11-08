package com.wangpeng.blog.drawerblog.beans;

/**
 * Created by WP on 2016/5/31.
 */

public class Comment {
    private String data;
    private String ip;
    private String id;
    private String content;

    public Comment(String data, String ip, String id, String content) {
        this.data = data;
        this.ip = ip;
        this.id = id;
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
