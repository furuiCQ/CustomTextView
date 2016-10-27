package com.frain.myapplication.ReviewFourthProject.model;

import java.io.Serializable;

/**
 * Created by admin on 2016/10/26.
 */
public class NewsList implements Serializable{
    public static final  long serialVersionUID=-1L;
    String title;//新闻标题
    String authorname;//新闻名字
    int type;//0为无图模式，1为一张图的模式，2为多图模式
    int[] images;//图片组
    int connectNumb;//评论数量
    long time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public int getConnectNumb() {
        return connectNumb;
    }

    public void setConnectNumb(int connectNumb) {
        this.connectNumb = connectNumb;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
