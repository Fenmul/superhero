package com.next.service;

import com.next.pojo.Movie;
import com.next.utils.JqGridResult;

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

    /**
     * 查询全部电影
     */
    List<Movie> queryAllMovies();

    /**
     * 根据关键字查询分页
     */
    JqGridResult searchTrailer(String keywords, Integer page, Integer pageSize);

    Movie queryMovieById(String id);
}
