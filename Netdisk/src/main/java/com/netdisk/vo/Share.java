package com.netdisk.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Share<T> {
    private int id;
    private String url;
    private String filename;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startime;
    private int livetime;
    private int userid;
    private int fileid;



    private List<T> data;

    public Share() {
    }

    public Share(int id,String filename,String url, Date startime, int livetime, int userid, int fileid,List<T> data) {
        this.id = id;
        this.filename = filename;
        this.url = url;
        this.startime = startime;
        this.livetime = livetime;
        this.userid = userid;
        this.fileid = fileid;
        this.data = data;//数据
    }

    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartime() {
        return startime;
    }

    public void setStartime(Date startime) {
        this.startime = startime;
    }

    public int getLivetime() {
        return livetime;
    }

    public void setLivetime(int livetime) {
        this.livetime = livetime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }
}
