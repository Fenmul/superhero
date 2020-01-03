package com.next.pojo.vo;

public class StaffVO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别：
     1： 男
     0： 女
     */
    private Integer sex;

    /**
     * 人物照片
     */
    private String photo;
    /**
     * 演职人员id
     */
    private String staffId;

    /**
     * 角色：
     1：导演
     2：演员
     */
    private Integer role;

    /**
     * 饰演人物
     */
    private String actName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }
}
