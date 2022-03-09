package com.netdisk.controller;

import com.netdisk.service.FileService;
import com.netdisk.service.UserService;
import com.netdisk.service.impl.FileServicelmpl;
import com.netdisk.vo.File_;
import com.netdisk.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileServicelmpl fileService;
    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;

    /**显示相对地址*/
    @Value("${file.upload.relative}")
    private String fileRelativePath;


    //跳转到上传页面
    @RequestMapping("/uploadForm")
    public String uploadForm(){
        return "/user/upload";
    }

    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public String upload(String str, MultipartFile file, ModelMap map) throws IOException {
        //获取文件原名
        String fileName = file.getOriginalFilename();
        //创建要上传的文件对象
        File newFile = new File(filePath,fileName);
        //将文件copy到目标地址
        file.transferTo(newFile);
        String type = fileName.substring(fileName.lastIndexOf(".")+1);
        Integer size=(int)file.getSize();
        File_ f=new File_(size, fileName, filePath+fileName, type, str);
        fileService.upload(f);
        map.put("imgPath",fileRelativePath.replace("*","")+fileName);
        return "/user/list";
    }

    //图片列表
    @RequestMapping("/listFiles")
    public String listFiles(ModelMap map, HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            return "redirect:/login";
        }
        File file = new File(filePath);
        //文件过滤
        String[] fileNames = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg") ){
                    return true;
                }
                return false;
            }
        });

        List<String> files = new ArrayList<>();
        if (fileNames!=null){
            for (String fs : fileNames){
                files.add(fileRelativePath.replace("*","")+fs);
            }
        }
        map.put("files",files);
        //map.put("imgPath","");
        return "/user/fileList";
    }

    //下载
    @RequestMapping("/download")
    public void download(String name, HttpServletResponse response) throws UnsupportedEncodingException {

        //截取路径中的名称
        name = name.substring(name.lastIndexOf("/")+1);
        // 下载时文件名会乱码或不显示，进行转码
        String newName = new String(name.getBytes("UTF-8"), "iso-8859-1");

        //设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        /**
         * 设置响应头
         * Content-Disposition属性有两种类型：
         * inline 和 attachment
         * inline ：将文件内容直接显示在页面
         * attachment：弹出对话框让用户选择下载
         */
        response.setHeader("Content-Disposition","attachment;filename="+newName+"");

        try {

            FileInputStream fis = new FileInputStream(filePath+name);

            //获取response的输出流
            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            //读取同时，用response的输出流 发送给客户端
            while((len=fis.read(buf))!=-1){
                outputStream.write(buf,0,len);
            }
            //关闭资源
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/find")
    public String find(String element, ModelMap map){
        map.put("files", fileService.find("%"+element+"%"));
        return "/user/findResult";
    }

    @RequestMapping("/fileFolder1")
    public String fileFolder1(ModelMap map,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        map.put("fileFolder1", fileService.folder1List(user.getId()));
        return "/user/fileFolder1";
    }

    @RequestMapping("/fileFolder2")
    public String fileFolder2(ModelMap map,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        map.put("fileFolder2", fileService.folder2List(user.getId()));
        return "/user/fileFolder2";
    }

    @RequestMapping("/move")
    public String filemove(String str1, String str2){
        fileService.filemove(str1, str2);
        return "redirect:/file/fileFolder"+(str2=="2"?"1":"2");
    }

    @RequestMapping("/flist")
    public String freturn(){
        return "redirect:/file/listFiles";
    }

    @RequestMapping("/fileFolder1File")
    public String listFolder1Files(Integer id,ModelMap map){

        List<String> filePathList=new ArrayList<>();
        //获得文件绝对路径
        String filePath=fileService.getFolder1Path(id).replaceAll("\\[|\\]","");
        String f=filePath.substring(filePath.lastIndexOf("/")+1);

        filePathList.add(fileRelativePath.replace("*","")+f);

        map.put("files",filePathList);
//        map.put("imgPath","");

        return "/user/fileFolderFileCheck";
    }



    @RequestMapping("/fileFolder2File")
    public String listFolder2Files(Integer id,ModelMap map){

        List<String> filePathList=new ArrayList<>();

        String filePath=fileService.getFolder2Path(id).replaceAll("\\[|\\]","");
        String f=filePath.substring(filePath.lastIndexOf("/")+1);

        filePathList.add(fileRelativePath.replace("*","")+f);

        //映射网页
        map.put("files",filePathList);
        map.put("imgPath","");

        return "/user/fileFolderFileCheck";
    }

    @RequestMapping("/deleteFile")
    public String deleteFileToBin(int id){
        //删除文件到回收站
        fileService.deleteFileToBin(id);
        return "/user/fileList";
    }

}
