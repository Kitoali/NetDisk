package com.netdisk.vo;

import java.util.Date;

public class DeleteStation {
    private int id;
    private int fileid;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deletetime;
    private int leavetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public int getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(int leavetime) {
        this.leavetime = leavetime;
    }
}
