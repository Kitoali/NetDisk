package com.netdisk.vo;

public class Condition {

    private String queryName;
    private Integer queryMinLivetime;
    private Integer queryMaxLivetime;

    public Condition() {
    }

    public Condition(String queryName, Integer queryMinLivetime, Integer queryMaxLivetime) {
        this.queryName = queryName;
        this.queryMinLivetime = queryMinLivetime;
        this.queryMaxLivetime = queryMaxLivetime;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public Integer getQueryMinLivetime() {
        return queryMinLivetime;
    }

    public void setQueryMinLivetime(Integer queryMinLivetime) {
        this.queryMinLivetime = queryMinLivetime;
    }

    public Integer getQueryMaxLivetime() {
        return queryMaxLivetime;
    }

    public void setQueryMaxLivetime(Integer queryMaxLivetime) {
        this.queryMaxLivetime = queryMaxLivetime;
    }
}
