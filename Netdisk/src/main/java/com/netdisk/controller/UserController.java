package com.netdisk.controller;

import com.netdisk.service.impl.UserServicelmpl;
import com.netdisk.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserServicelmpl userService;

    @RequestMapping("list")
    public String index(ModelMap map){
        map.put("users",userService.list());
        return "/user/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        userService.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/addForm")
    public String addForm(){
        return "/user/addUser";
    }

    @RequestMapping("/add")
    public String add(User user){
        userService.add(user);
        return "redirect:/list";
    }

    @RequestMapping("/updateForm")
    public String updataForm(Integer id, ModelMap map){
        User user=userService.getEmpById(id);
        map.put("user",user);

        return "/user/updateUser";
    }

    @RequestMapping("/update")
    public String updata(User user){
        userService.update(user);
        return "redirect:/list";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "/user/login";
    }

    @RequestMapping("isFlag")
    @ResponseBody
    public String isFlag(String username){
        return userService.isFlag(username);
    }

    @RequestMapping("toLogin")
    @ResponseBody
    public boolean toLogin(String username, String password, HttpServletRequest request){
        User i = userService.toLogin(username, password);
        if (i!=null){
            request.getSession().setAttribute("user", i);
            i.setFlag(1);
            userService.updateFlag(i.getId());
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/toChangePassword")
    public String toChangePassword(){
        return "/user/changePassword";
    }

    @RequestMapping("changePassword")
    @ResponseBody
    public void changePassword(String password, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        userService.changePassword(user.getId(), password);
    }

    @RequestMapping("/toforgetPassword")
    public String toforgetPassword(){
        return "/user/forgetPassword";
    }

    @RequestMapping("forgetPassword")
    @ResponseBody
    public Boolean forgetPassword(String username,String password){
        int i = userService.forgetPassword(username, password);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }
}
