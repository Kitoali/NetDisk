package com.netdisk.service.impl;

import com.netdisk.mapper.DeleteStationMapper;
import com.netdisk.mapper.FileMapper;
import com.netdisk.service.DeleteStationService;
import com.netdisk.vo.DeleteStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteStationImpl implements DeleteStationService {
    @Autowired
    private DeleteStationMapper deleteStationMapper;
    @Autowired
    private FileMapper fileMapper;

    //查看回收站
    public List<DeleteStation> listDeleteStation(){

        return deleteStationMapper.listDeleteStation();
    }
    //删除回收站
    public void deleteBin(int id,int fileid){
        //删除回收站记录
        deleteStationMapper.deleteBin(id);
        //删除文件记录
        fileMapper.deleteFile(fileid);
    }
    //恢复文件
    public void recoverBin(int id,int fileid){
        //更改文件状态
        fileMapper.recoverFile(fileid);
        //删除回收站记录
        deleteStationMapper.deleteBin(id);
    }
}
