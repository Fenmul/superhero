package com.next.pojo;

import javax.persistence.*;

public class Carousel {
    @Id
    private String id;

    /**
     * 电影id，页面跳转需要用到
     */
    @Column(name = "movie_id")
    private String movieId;

    /**
     * 图片地址
     */
    private String image;

    /**
     * 排序，从零开始，数字越小优先级越大
     */
    private Integer sort;

    /**
     * 是否展示：
1：展示
0：不展示
     */
    @Column(name = "is_show")
    private Integer isShow;

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
     * 获取电影id，页面跳转需要用到
     *
     * @return movie_id - 电影id，页面跳转需要用到
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * 设置电影id，页面跳转需要用到
     *
     * @param movieId 电影id，页面跳转需要用到
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    /**
     * 获取图片地址
     *
     * @return image - 图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片地址
     *
     * @param image 图片地址
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取排序，从零开始，数字越小优先级越大
     *
     * @return sort - 排序，从零开始，数字越小优先级越大
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序，从零开始，数字越小优先级越大
     *
     * @param sort 排序，从零开始，数字越小优先级越大
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否展示：
1：展示
0：不展示
     *
     * @return is_show - 是否展示：
1：展示
0：不展示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置是否展示：
1：展示
0：不展示
     *
     * @param isShow 是否展示：
1：展示
0：不展示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}