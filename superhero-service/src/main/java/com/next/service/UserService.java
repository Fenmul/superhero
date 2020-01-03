package com.next.service;

import com.next.pojo.Users;
import com.next.pojo.bo.MPWXUserBO;

public interface UserService {
    /**
     * 查询小程序登录的用户信息
     * @param openId openId
     * @return 用户信息
     */
    Users queryUserLoginByMPWX(String openId);

    /**
     * 通过微信小程序登陆注册
     * @param openId openId
     * @param mpwxUserBO 小程序用户信息
     * @return 用户信息
     */
    Users saveUserMPWX(String openId, MPWXUserBO mpwxUserBO);
}
