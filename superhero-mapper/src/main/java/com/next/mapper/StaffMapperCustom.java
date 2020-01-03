package com.next.mapper;

import com.next.mymapper.MyMapper;
import com.next.pojo.Staff;
import com.next.pojo.vo.StaffVO;
import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StaffMapperCustom extends MyMapper<Staff> {

    /**
     * 根据预告片的id和角色查询导演列表或者演员列表
     * 通过 param 注解实现映射
     */
//    @Select(" SELECT sta.id as staffId, sta.`name` as name, sta.sex as sex, sta.photo as photo, rel.role as role, rel.act_name as actName FROM staff_movie rel LEFT JOIN staff sta on rel.staff_id = sta.id WHERE rel.movie_id = #{trailerId} AND rel.role = #{role}")
     List<StaffVO> queryStaffs(
            @Param(value = "trailerId") String trailerId,
            @Param(value = "role") Integer role);

}