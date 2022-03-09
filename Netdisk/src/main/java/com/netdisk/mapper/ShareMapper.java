package com.netdisk.mapper;


import com.netdisk.vo.Condition;
import com.netdisk.vo.Share;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShareMapper {

    @Select("select * from t_share")
    List<Share> myShare();


    @Insert("insert into t_share(url,startime,livetime,userid,fileid) values(#{share.url},#{share.startime},#{share.livetime},#{share.userid},#{share.fileid})")
    void addShare(@Param("share") Share share);

    @Delete("delete from t_share where id = #{id}")
    void deleteShare(Integer id);

    @Select("select * from t_share where id=#{id}")
    Share getShareById(Integer id);

    @Update("update t_share set livetime=#{livetime} where id=#{id}")
    void updateShare(Share share);


    @Select({"<script> select t_share.id,url,startime,livetime,filename from t_share join t_file on t_share.fileid=t_file.id" +
            "<where> " +
//           这里的name要通过文件的ID拿到文件名,需要再次添加 select filename from t_file where id=#{fileid}
            "<if test='condition.queryName != null'> and filename like '%${condition.queryName}%' </if> "+
            "<if test='condition.queryMinLivetime != null'> and livetime &gt;= #{condition.queryMinLivetime} </if> "+
            "<if test='condition.queryMaxLivetime != null'> and livetime &lt;= #{condition.queryMaxLivetime} </if> "+
            "</where> "+
            " limit #{recordStart},#{pageSize} " +
            "</script>"})
    List<Share> queryShare(@Param("recordStart") Integer recordStar, @Param("pageSize") Integer pageSize, @Param("condition") Condition condition);


    //获取总记录条数
    @Select({"<script> select count(*) from t_share join t_file on t_share.fileid=t_file.id" +
            "<where> " +
            "<if test='condition.queryName != null'> and filename like '%${condition.queryName}%' </if> "+
            "<if test='condition.queryMinLivetime != null'> and livetime &gt;= #{condition.queryMinLivetime} </if> "+
            "<if test='condition.queryMaxLivetime != null'> and livetime &lt;= #{condition.queryMaxLivetime} </if> "+
            "</where> "+
            "</script>"})
    int getCount(@Param("condition") Condition condition);
}
