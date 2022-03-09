package com.netdisk.service;

import com.netdisk.vo.DeleteStation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeleteStationService {
    List<DeleteStation> listDeleteStation();
    public void deleteBin(int id, int fileid);
    public void recoverBin(int id, int fileid);
}
