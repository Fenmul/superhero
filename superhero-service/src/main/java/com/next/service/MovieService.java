package com.next.service;

import com.next.pojo.Movie;

import java.util.List;

public interface MovieService {
    /**
     * 查询热门预告
     * 电影根据评分，预告根据点赞
     * @param type superhero（电影）/trailer（预告片）
     * @return 电影集合
     */
    List<Movie> queryHotSuperHero(String type);

    /**
     * 查询电影预告表的记录数
     *
     * @return 总数
     */
    Integer queryAllTrailerCounts();

    List<Movie> queryAllMovies();
}
