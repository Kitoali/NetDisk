package com.netdisk.vo;

import java.util.List;

public class Pages<T> {
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalRecord;
    private Integer pageIndex;
    private List<T> data;

    public Pages() {
    }

    public Pages(Integer pageSize, Integer totalPage, Integer totalRecord, Integer pageIndex, List<T> data) {
        this.pageSize = pageSize;//每页条数
        this.totalPage = totalPage;//分页总数
        this.totalRecord = totalRecord;//信息条数
        this.pageIndex = pageIndex;//当前页
        this.data = data;//数据
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
