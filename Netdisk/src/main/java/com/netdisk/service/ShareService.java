package com.netdisk.service;


import com.netdisk.mapper.ShareMapper;
import com.netdisk.vo.Condition;
import com.netdisk.vo.Pages;
import com.netdisk.vo.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {
    @Autowired
    private ShareMapper shareMapper;
    public List<Share> myShare(){
        return shareMapper.myShare();
    }

    public void addShare(Share share) {

        shareMapper.addShare(share);
    }


    public void deleteShare(Integer id) {
        shareMapper.deleteShare(id);
    }

    public Share getShareById(Integer id) {

        return shareMapper.getShareById(id);
    }

    public void updateShare(Share share) {

        shareMapper.updateShare(share);
    }

    public Pages<Share> queryShare(Integer pageIndex, Integer pageSize, Condition condition) {
        //计算当前页的起始条数
        Integer recordStart = (pageIndex-1)*pageSize;
        //获取当前页的数据
        List<Share> shares = shareMapper.queryShare(recordStart,pageSize,condition);
        //获取共多少条
        int totalRecord = shareMapper.getCount(condition);
        //计算总页数
        Integer totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
        //组装pages对象
        Pages<Share> pages = new Pages<>(pageSize,totalPage,totalRecord,pageIndex,shares);

        return pages;
    }

}
