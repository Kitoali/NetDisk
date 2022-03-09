package com.netdisk.service.impl;

import com.netdisk.mapper.UserMapper;
import com.netdisk.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl {
    @Autowired
    private UserMapper userMapper;

    public List<User> list(){
        return userMapper.list();
    }

    public void delete(Integer id) {
        userMapper.delete(id);
    }

    public void add(User user) {
        userMapper.add(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public User getEmpById(Integer id) {
        return userMapper.getEmpById(id);

    }

    public String isFlag(String username) {
        return userMapper.isFlag(username);
    }

    public User toLogin(String username,String password){
        return userMapper.toLogin(username,password);
    }

    public void updateFlag(int id){
        userMapper.updateFlag(id);
    }

    public void changePassword(int id,String password){
        userMapper.changePassword(id,password);
    }

    public int forgetPassword(String username, String password){
        return userMapper.forgetPassword(username, password);
    }

}
