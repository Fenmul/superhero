package com.next.service.impl;

import com.next.mapper.UsersMapper;
import com.next.pojo.Users;
import com.next.pojo.bo.MPWXUserBO;
import com.next.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    /**
     * Twitter idworker 生成顺序ID
     */
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserLoginByMPWX(String openId) {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("mpWxOpenId", openId);
        return usersMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users saveUserMPWX(String openId, MPWXUserBO mpwxUserBO) {
        Users user = new Users();
        user.setMpWxOpenId(openId);
        user.setNickname(mpwxUserBO.getNickName());
        user.setFaceImage(mpwxUserBO.getAvatarUrl());

        user.setRegistTime(new Date());
        user.setBirthday("1900-01-01");
        user.setIsCertified(0);
        /*
         生成 ID 的 3 种方法
         1. ID 自增「分库后无法处理」
         2. UUID 「id 没有顺序」
         3. Twitter idWorker
         */
        user.setId(sid.nextShort());
        usersMapper.insert(user);
        return user;
    }
}
