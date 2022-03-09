package com.netdisk.controller;

import com.netdisk.service.ShareService;
import com.netdisk.service.impl.FileServicelmpl;
import com.netdisk.vo.Condition;
import com.netdisk.vo.Pages;
import com.netdisk.vo.Share;
import com.netdisk.vo.User;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ShareController {
    @Autowired
    private FileServicelmpl fileService;
    @Autowired
    private ShareService shareService;

//    @RequestMapping("/addShareForm")
//    public String addForm() {
//
//        return "share/addShare";
//    }
//@ResponseBody
//@RequestMapping("/fileFolder1File")
//    public String createUrl(Integer id, HttpServletRequest request){
//
//        String basePath = request.getScheme() + "://" + request.getServerName()
//            + ":" + request.getServerPort();
//        String url =basePath+"/file/fileFolder1File?id="+id;
//
//        return url;
//    }
//    @RequestMapping(value = "/addShare", method = RequestMethod.POST)
    @RequestMapping("/addShareForm")
    public String addShare(Integer id, ModelMap map, Share share, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort();

        String folder = fileService.getFolderID(id);
        String url =basePath+"/file/fileFolder"+folder+"File?id="+id;
//        String url = createUrl(id);

        share.setUrl(url);
        share.setLivetime(5);
        share.setUserid(user.getId());
        share.setFileid(id);

        map.put("share",share);
        shareService.addShare(share);

        return "/share/shareUrl";
    }



    @RequestMapping("/updateShareForm")
    public String updateForm(Integer id, ModelMap map) {

        Share share = shareService.getShareById(id);
        map.put("share", share);

        return "share/updateShare";
    }

    @RequestMapping("/updateShare")
    public String updateShare(Share share) {

        shareService.updateShare(share);
        return "redirect:/queryShare";
    }

//    @RequestMapping("/deleteShare")
//    public String deleteShare(Integer id) {
//
//        shareService.deleteShare(id);
//        //重定向
//        return "redirect:/queryShare";
//    }
//
    @ResponseBody
    @RequestMapping("/deleteShare")
    public void deleteShare(Integer id,HttpServletResponse response) throws IOException{
        shareService.deleteShare(id);
        response.sendRedirect("/queryShare");
    }

    //    @RequestMapping(value="/delAll",method = RequestMethod.GET)
//    public String delAll(@RequestParam(value="ids") String ids) {
//        String[] str = ids.split(",");
//        Integer[] dosageArray = (Integer[]) ConvertUtils.convert(str, Integer.class);//这块循环就能读取数据
//        for(int i = 0;i < dosageArray.length; i++){
//            shareService.deleteShare(dosageArray[i]);
//        }
////        System.out.println(ids);
//        return "redirect:/queryShare";
//    }
    @ResponseBody
    @RequestMapping(value="/delAll",method = RequestMethod.GET)
    public void  delAll(@RequestParam(value="ids") String ids, HttpServletResponse response) throws IOException {
        String[] str = ids.split(",");
        Integer[] dosageArray = (Integer[]) ConvertUtils.convert(str, Integer.class);//这块循环就能读取数据
        for(int i = 0;i < dosageArray.length; i++){
            shareService.deleteShare(dosageArray[i]);
        }
        response.sendRedirect("/queryShare");//重定向页面不跳转，不知道原因和解决方案
    }

    @RequestMapping("/queryShare")
    public String queryShare(
            @RequestParam(value="pageIndex",defaultValue="1",required=false)Integer pageIndex,
            @RequestParam(value="pageSize",defaultValue="5",required=false)Integer pageSize,
            Condition condition, ModelMap modelMap, HttpServletRequest request) {
        //空指针
        User user = (User) request.getSession().getAttribute("user");

        Pages<Share> pages = shareService.queryShare(pageIndex,pageSize,condition);
        modelMap.put("pages",pages);
        modelMap.put("condition",condition);
        //请求转发1
        return "share/myShare";
    }


    /**
     * 生成文件分享链接
     * a
     * @return
     */
    //可以参照add student
//    @RequestMapping("/share")
//    public String share(HttpServletRequest request,Share share,ModelMap map) {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
//
//        String format = sdf.format(new Date());
//        //确定生成时间 startime
//
//        //待定->设定有效时间livetime
//
//
//        String shareUrl = request.getScheme() + "://" + request.getServerName()
//                + ":" + request.getServerPort()  +"/"+ format + share.getFileid();
//
//        char[] shUrl = shareUrl.toCharArray();
//        share.setUrl(shUrl);
//
//        map.put("shareUrl",shareUrl);
//        map.put("share",share);
//        return "/share";
//    }


}
