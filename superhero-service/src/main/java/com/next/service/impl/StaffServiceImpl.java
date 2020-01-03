package com.next.service.impl;

import com.next.mapper.StaffMapperCustom;
import com.next.pojo.vo.StaffVO;
import com.next.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapperCustom staffMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<StaffVO> queryStaffs(String trailerId, Integer role) {
        return staffMapperCustom.queryStaffs(trailerId, role);
    }
}
