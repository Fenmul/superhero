package com.next.service;

import com.next.pojo.Users;
import com.next.pojo.bo.MPWXUserBO;
import com.next.pojo.bo.RegistLoginUsersBO;

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

    /**
     * 查询用户是否存在
     * @param username 用户名
     * @return {boolean}
     */
    boolean queryUserIsExists(String username);

    /**
     * 登陆用户「注册用户」
     * @param registLoginUsersBO 注册用户登陆
     * @return 登陆用户信息
     */
    Users loginRegister(RegistLoginUsersBO registLoginUsersBO) throws Exception;

    /**
     * 保存注册用户信息
     * @param registLoginUsersBO 注册用户
     * @return 注册成功的用户信息
     */
    Users saveUserRegister(RegistLoginUsersBO registLoginUsersBO) throws Exception;

}
