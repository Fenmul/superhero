package com.next.service;

import com.next.pojo.vo.StaffVO;

import java.util.List;

public interface StaffService {
    /**
     * 查询演职人员列表
     * @param trailerId 电影 ID
     * @param role 角色
     * @return StaffVO
     */
    List<StaffVO> queryStaffs(String trailerId, Integer role);
}
