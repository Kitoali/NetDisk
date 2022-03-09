package com.netdisk.vo;

import lombok.Data;

@Data
public class File_ {//id自增长
    public Integer id, size, userid;
    public String filename, path, state, type, folder;
    public File_(Integer size, String filename, String path, String type, String folder){
        this.size=size;
        //this.userid=userid;
        this.filename=filename;
        this.path=path;
        this.type=type;
        this.folder=folder;
    }
}
