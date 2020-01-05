package com.next.service.impl;

import com.next.mapper.UsersMapper;
import com.next.pojo.Users;
import com.next.pojo.bo.MPWXUserBO;
import com.next.pojo.bo.RegistLoginUsersBO;
import com.next.service.UserService;
import com.next.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
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
     * 默认头像图片
     */
    private final static String USER_DEFAULT_FACE_IMAGE_URL = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserIsExists(String username) {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("username", username);
        Users user = usersMapper.selectOneByExample(example);
        return user != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users loginRegister(RegistLoginUsersBO registLoginUsersBO) throws Exception {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("username", registLoginUsersBO.getUsername());
        Users user = usersMapper.selectOneByExample(example);
        if (StringUtils.equals(user.getPassword(), MD5Utils.getMD5Str(registLoginUsersBO.getPassword()))) {
            return user;
        } else {
            return null;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users saveUserRegister(RegistLoginUsersBO registLoginUsersBO) throws Exception {
        Users user = new Users();

        user.setUsername(registLoginUsersBO.getUsername());
        user.setPassword(MD5Utils.getMD5Str(registLoginUsersBO.getPassword()));
        user.setNickname(registLoginUsersBO.getUsername());
        user.setFaceImage(USER_DEFAULT_FACE_IMAGE_URL);

        user.setRegistTime(new Date());
        user.setBirthday("1900-01-01");
        user.setIsCertified(0);
        user.setId(sid.nextShort());
        usersMapper.insert(user);
        return user;
    }
}
