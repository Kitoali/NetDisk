package com.netdisk.service;

import com.netdisk.vo.User;

import java.util.List;

public interface UserService {
    public List<User> list();
    public void delete(Integer id);
    public void add(User user);
    public void update(User user);
    public User getEmpById(Integer id);
    public void find(String element);
    public void updateFlag(int id);
    public void changePassword(int id,String password);


}
