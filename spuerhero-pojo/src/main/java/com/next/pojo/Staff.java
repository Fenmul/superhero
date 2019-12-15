package com.next.pojo;

import javax.persistence.*;

public class Staff {
    @Id
    private String id;

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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别：
1： 男
0： 女
     *
     * @return sex - 性别：
1： 男
0： 女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别：
1： 男
0： 女
     *
     * @param sex 性别：
1： 男
0： 女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取人物照片
     *
     * @return photo - 人物照片
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置人物照片
     *
     * @param photo 人物照片
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}