package com.next.pojo;
import javax.persistence.*;

@Table(name = "staff_movie")
public class StaffMovie {
    @Id
    private String id;

    /**
     * 演职人员id
     */
    @Column(name = "staff_id")
    private String staffId;

    /**
     * 电影id
     */
    @Column(name = "movie_id")
    private String movieId;

    /**
     * 角色：
1：导演
2：演员
     */
    private Integer role;

    /**
     * 饰演人物
     */
    @Column(name = "act_name")
    private String actName;

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
     * 获取演职人员id
     *
     * @return staff_id - 演职人员id
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置演职人员id
     *
     * @param staffId 演职人员id
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取电影id
     *
     * @return movie_id - 电影id
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * 设置电影id
     *
     * @param movieId 电影id
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    /**
     * 获取角色：
1：导演
2：演员
     *
     * @return role - 角色：
1：导演
2：演员
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置角色：
1：导演
2：演员
     *
     * @param role 角色：
1：导演
2：演员
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * 获取饰演人物
     *
     * @return act_name - 饰演人物
     */
    public String getActName() {
        return actName;
    }

    /**
     * 设置饰演人物
     *
     * @param actName 饰演人物
     */
    public void setActName(String actName) {
        this.actName = actName;
    }
}