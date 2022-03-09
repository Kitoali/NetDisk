package com.netdisk.controller;


import com.netdisk.service.impl.DeleteStationImpl;
import com.netdisk.vo.DeleteStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DeleteStationController {
    @Autowired
    private DeleteStationImpl deleteStationService;
    //查看回收站
    @RequestMapping("/listDelete")
    public String listProduct(ModelMap modelMap) {

        List<DeleteStation> deletes = deleteStationService.listDeleteStation();
        modelMap.put("deletes", deletes);
        //请求转发
        return "RecycleBin/listDelete";
    }
    //删除回收站
    @RequestMapping(value = "/deleteBin", method = RequestMethod.GET)
    public String deleteBin(int id,int fileid) {
        deleteStationService.deleteBin(id,fileid);
        return "redirect:/listDelete";
    }
    //恢复文件
    @RequestMapping(value = "/recoverBin", method = RequestMethod.GET)
    public String recoverBin(int id,int fileid){
        deleteStationService.recoverBin(id,fileid);
        return "redirect:/listDelete";
    }

}
