package com.netdisk.service.impl;

import com.netdisk.mapper.DeleteStationMapper;
import com.netdisk.mapper.FileMapper;
import com.netdisk.vo.DeleteStation;
import com.netdisk.vo.File_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServicelmpl {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private DeleteStationMapper deleteStationMapper;

    public List<File_> find(String element){
        return fileMapper.find(element);
    }

    public void upload(File_ f) {
        fileMapper.upload(f);
    }

    public List<File_> folder1List(int id) {return fileMapper.folder1List(id);
    }

    public List<File_> folder2List(int id) {
        return fileMapper.folder2List(id);
    }

    public void filemove(String str1, String str2) {
        fileMapper.filemove(str1, str2);
    }
    public String getFolder1Path(Integer id) {
        return fileMapper.getFolder1FilePath(id);
    }

    public String getFolder2Path(Integer id) {
        return fileMapper.getFolder2FilePath(id);
    }

    public String getFolderID(Integer id) {
        return fileMapper.getFolderID(id);
    }

    public void deleteFileToBin(int id){
        //添加回收站记录
        DeleteStation deleteStation=new DeleteStation();
        deleteStation.setFileid(id);
        deleteStation.setLeavetime(20);
        deleteStationMapper.addBin(deleteStation);
        //更改文件状态 1-0
        fileMapper.deleteFileToBin(id);

    }
}
