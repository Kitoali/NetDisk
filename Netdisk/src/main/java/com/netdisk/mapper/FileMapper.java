package com.netdisk.mapper;

import com.netdisk.vo.File_;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan("com.netdisk.mapper")
public interface FileMapper {
    //    @Select("select * from t_file")
    @Select("select * from t_file where filename like #{element}")
    List<File_> find(@Param("element") String element);
    @Insert("insert into t_file(filename, path, type, size, userid, state, folder) values(#{f.filename}, #{f.path}, #{f.type}, #{f.size}, 1,  1, #{f.folder})")
    void upload(@Param("f") File_ f);
    @Select("select * from t_file where folder='1' && state='1'")
    List<File_> folder1List(int id);
    @Select("select * from t_file where folder='2' && state='1'")
    List<File_> folder2List(int id);
    @Update("update t_file set folder=#{str2} where id=#{str1}")
    void filemove(@Param("str1") String str1, @Param("str2") String str2);
    @Select("select path from t_file where folder='1' and id=#{id}")
    String getFolder1FilePath(Integer id);
    @Select("select path from t_file where folder='2' and id=#{id}")
    String getFolder2FilePath(Integer id);
    //删除文件记录
    @Delete("delete from t_file where id = #{id}")
    void deleteFile(int id);
    //更改文件状态 删
    @Update("update t_file set state = replace(state, '1' , '0') where id = #{id}")
    void deleteFileToBin(int id);
    //更改文件状态 恢复
    @Update("update t_file set state = replace(state, '0' , '1') where id = #{id}")
    void recoverFile(int id);
    @Select("select folder from t_file where id=#{id}")
    String getFolderID(Integer id);
}
