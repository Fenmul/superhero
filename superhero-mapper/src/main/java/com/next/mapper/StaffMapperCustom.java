package com.next.mapper;

import com.next.mymapper.MyMapper;
import com.next.pojo.Staff;
import com.next.pojo.vo.StaffVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapperCustom extends MyMapper<Staff> {

    /**
     * 根据预告片的id和角色查询导演列表或者演员列表
     * 通过 param 注解实现映射
     */
    public List<StaffVO> queryStaffs(
            @Param(value = "trailerId") String trailerId,
            @Param(value = "role") Integer role);

}