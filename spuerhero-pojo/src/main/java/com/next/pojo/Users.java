package com.next.pojo;

import java.util.Date;
import javax.persistence.*;

public class Users {
    @Id
    private String id;

    private String username;

    private String password;

    private String nickname;

    /**
     * 其实就是openId
     */
    @Column(name = "mp_wx_open_id")
    private String mpWxOpenId;

    @Column(name = "app_qq_open_id")
    private String appQqOpenId;

    @Column(name = "app_wx_open_id")
    private String appWxOpenId;

    @Column(name = "app_weibo_uid")
    private String appWeiboUid;

    private Integer sex;

    private String birthday;

    @Column(name = "face_image")
    private String faceImage;

    /**
     * 是否实名认证
1：已实名
0：未实名
     */
    @Column(name = "is_certified")
    private Integer isCertified;

    @Column(name = "regist_time")
    private Date registTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取其实就是openId
     *
     * @return mp_wx_open_id - 其实就是openId
     */
    public String getMpWxOpenId() {
        return mpWxOpenId;
    }

    /**
     * 设置其实就是openId
     *
     * @param mpWxOpenId 其实就是openId
     */
    public void setMpWxOpenId(String mpWxOpenId) {
        this.mpWxOpenId = mpWxOpenId;
    }

    /**
     * @return app_qq_open_id
     */
    public String getAppQqOpenId() {
        return appQqOpenId;
    }

    /**
     * @param appQqOpenId
     */
    public void setAppQqOpenId(String appQqOpenId) {
        this.appQqOpenId = appQqOpenId;
    }

    /**
     * @return app_wx_open_id
     */
    public String getAppWxOpenId() {
        return appWxOpenId;
    }

    /**
     * @param appWxOpenId
     */
    public void setAppWxOpenId(String appWxOpenId) {
        this.appWxOpenId = appWxOpenId;
    }

    /**
     * @return app_weibo_uid
     */
    public String getAppWeiboUid() {
        return appWeiboUid;
    }

    /**
     * @param appWeiboUid
     */
    public void setAppWeiboUid(String appWeiboUid) {
        this.appWeiboUid = appWeiboUid;
    }

    /**
     * @return sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return face_image
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * @param faceImage
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取是否实名认证
1：已实名
0：未实名
     *
     * @return is_certified - 是否实名认证
1：已实名
0：未实名
     */
    public Integer getIsCertified() {
        return isCertified;
    }

    /**
     * 设置是否实名认证
1：已实名
0：未实名
     *
     * @param isCertified 是否实名认证
1：已实名
0：未实名
     */
    public void setIsCertified(Integer isCertified) {
        this.isCertified = isCertified;
    }

    /**
     * @return regist_time
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * @param registTime
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}