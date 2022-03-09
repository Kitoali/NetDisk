package com.netdisk.service;

import com.netdisk.vo.File_;

import java.util.List;

public interface FileService {

    public List<File_> find(String element);
    public void upload(File_ f);
    public List<File_> folder1List();
    public List<File_> folder2List();
    public void filemove(String str1, String str2);
    public String getFolder1Path(Integer id);
    public String getFolder2Path(Integer id);
}
