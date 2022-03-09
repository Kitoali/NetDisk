package com.netdisk.mapper;

import com.netdisk.vo.DeleteStation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan("com.netdisk.mapper")
public interface DeleteStationMapper {
    //查找回收站记录
    @Select("select * from t_deletestation")
    List<DeleteStation> listDeleteStation();
    //删除回收站记录
    @Delete("delete from t_deletestation where id = #{id}")
    void deleteBin(int id);
    //添加回收站记录
    @Insert("insert into t_deletestation(fileid,deletetime,leavetime) values (#{deleteStation.fileid},now(),#{deleteStation.leavetime})")
    void addBin(@Param("deleteStation") DeleteStation deleteStation);

}
