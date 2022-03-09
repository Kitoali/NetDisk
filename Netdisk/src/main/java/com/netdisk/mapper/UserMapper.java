package com.netdisk.mapper;

import com.netdisk.vo.User;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user")
    List<User> list();
    @Delete("delete from t_user where id=#{id}")
    void delete(@Param("id") Integer id);
    @Insert("insert into t_user values(#{user.id}, #{user.adminid},#{user.password},#{user.phone},#{user.sex},#{user.state},#{user.username})")
    void add(@Param("user") User user);
    @Update("update t_user set id=#{user.id}, adminid=#{user.adminid},password=#{user.password},phone=#{user.phone},sex=#{user.sex},state=#{user.state},username=#{user.username} where id=#{user.id}")
    void update(@Param("user") User user);
    @Select("select * from t_user where id=#{id}")
    User getEmpById(@Param("id") Integer id);

    @Select("select flag from t_user where phone=#{username}")
    String isFlag(String username);

    @Select("select * from t_user where phone=#{username} and password = #{password}")
    User toLogin(@Param("username") String username,@Param("password") String password);

    @Update("update t_user set flag = 1 where id=#{id}")
    void updateFlag(int id);

    @Update("update t_user set password = #{password} where id=#{id}")
    void changePassword(@Param("id")int id,@Param("password")String password);

    @Update("update t_user set password = #{password} where phone = #{username}")
    int forgetPassword(@Param("username")String username, @Param("password")String password);
}
