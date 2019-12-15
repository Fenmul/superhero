package com.next.pojo;

import java.util.Date;
import javax.persistence.*;

public class Movie {
    @Id
    private String id;

    /**
     * 电影名称
     */
    private String name;

    /**
     * 海报
     */
    private String poster;

    /**
     * 电影封面
     */
    private String cover;

    /**
     * 预告片的播放地址
     */
    private String trailer;

    private Float score;

    /**
     * 影片获得赞的数量
     */
    @Column(name = "prised_counts")
    private Integer prisedCounts;

    /**
     * 基本介绍，例：
2018 / 美国 / 科幻 / 超级英雄
     */
    @Column(name = "basic_info")
    private String basicInfo;

    /**
     * 电影原名，例：
原名：Super Man
     */
    @Column(name = "original_name")
    private String originalName;

    /**
     * 上映时间：2018-12-17（中国大陆）
     */
    @Column(name = "release_date")
    private String releaseDate;

    /**
     * 影片时长：189分钟（中国大陆）
     */
    @Column(name = "total_time")
    private String totalTime;

    /**
     * 剧情介绍
     */
    @Column(name = "plot_desc")
    private String plotDesc;

    /**
     * 导演，数组表示，一部电影可以有多个或者至少1个，例：
["1001"，"1002"]
     */
    private String directors;

    /**
     * 主要演员，数组表示，例：
["", "", ""]
     */
    private String actors;

    /**
     * 剧照，数组表示，例如：
["","",""]
     */
    @Column(name = "plot_pics")
    private String plotPics;

    /**
     * 创建时间，保持和电影上映时间一致
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取电影名称
     *
     * @return name - 电影名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置电影名称
     *
     * @param name 电影名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取海报
     *
     * @return poster - 海报
     */
    public String getPoster() {
        return poster;
    }

    /**
     * 设置海报
     *
     * @param poster 海报
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * 获取电影封面
     *
     * @return cover - 电影封面
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置电影封面
     *
     * @param cover 电影封面
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 获取预告片的播放地址
     *
     * @return trailer - 预告片的播放地址
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * 设置预告片的播放地址
     *
     * @param trailer 预告片的播放地址
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    /**
     * @return score
     */
    public Float getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * 获取影片获得赞的数量
     *
     * @return prised_counts - 影片获得赞的数量
     */
    public Integer getPrisedCounts() {
        return prisedCounts;
    }

    /**
     * 设置影片获得赞的数量
     *
     * @param prisedCounts 影片获得赞的数量
     */
    public void setPrisedCounts(Integer prisedCounts) {
        this.prisedCounts = prisedCounts;
    }

    /**
     * 获取基本介绍，例：
2018 / 美国 / 科幻 / 超级英雄
     *
     * @return basic_info - 基本介绍，例：
2018 / 美国 / 科幻 / 超级英雄
     */
    public String getBasicInfo() {
        return basicInfo;
    }

    /**
     * 设置基本介绍，例：
2018 / 美国 / 科幻 / 超级英雄
     *
     * @param basicInfo 基本介绍，例：
2018 / 美国 / 科幻 / 超级英雄
     */
    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    /**
     * 获取电影原名，例：
原名：Super Man
     *
     * @return original_name - 电影原名，例：
原名：Super Man
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * 设置电影原名，例：
原名：Super Man
     *
     * @param originalName 电影原名，例：
原名：Super Man
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * 获取上映时间：2018-12-17（中国大陆）
     *
     * @return release_date - 上映时间：2018-12-17（中国大陆）
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 设置上映时间：2018-12-17（中国大陆）
     *
     * @param releaseDate 上映时间：2018-12-17（中国大陆）
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 获取影片时长：189分钟（中国大陆）
     *
     * @return total_time - 影片时长：189分钟（中国大陆）
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     * 设置影片时长：189分钟（中国大陆）
     *
     * @param totalTime 影片时长：189分钟（中国大陆）
     */
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * 获取剧情介绍
     *
     * @return plot_desc - 剧情介绍
     */
    public String getPlotDesc() {
        return plotDesc;
    }

    /**
     * 设置剧情介绍
     *
     * @param plotDesc 剧情介绍
     */
    public void setPlotDesc(String plotDesc) {
        this.plotDesc = plotDesc;
    }

    /**
     * 获取导演，数组表示，一部电影可以有多个或者至少1个，例：
["1001"，"1002"]
     *
     * @return directors - 导演，数组表示，一部电影可以有多个或者至少1个，例：
["1001"，"1002"]
     */
    public String getDirectors() {
        return directors;
    }

    /**
     * 设置导演，数组表示，一部电影可以有多个或者至少1个，例：
["1001"，"1002"]
     *
     * @param directors 导演，数组表示，一部电影可以有多个或者至少1个，例：
["1001"，"1002"]
     */
    public void setDirectors(String directors) {
        this.directors = directors;
    }

    /**
     * 获取主要演员，数组表示，例：
["", "", ""]
     *
     * @return actors - 主要演员，数组表示，例：
["", "", ""]
     */
    public String getActors() {
        return actors;
    }

    /**
     * 设置主要演员，数组表示，例：
["", "", ""]
     *
     * @param actors 主要演员，数组表示，例：
["", "", ""]
     */
    public void setActors(String actors) {
        this.actors = actors;
    }

    /**
     * 获取剧照，数组表示，例如：
["","",""]
     *
     * @return plot_pics - 剧照，数组表示，例如：
["","",""]
     */
    public String getPlotPics() {
        return plotPics;
    }

    /**
     * 设置剧照，数组表示，例如：
["","",""]
     *
     * @param plotPics 剧照，数组表示，例如：
["","",""]
     */
    public void setPlotPics(String plotPics) {
        this.plotPics = plotPics;
    }

    /**
     * 获取创建时间，保持和电影上映时间一致
     *
     * @return create_time - 创建时间，保持和电影上映时间一致
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间，保持和电影上映时间一致
     *
     * @param createTime 创建时间，保持和电影上映时间一致
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}