package com.next.api.config;

import com.next.pojo.Movie;
import com.next.redis.RedisOperator;
import com.next.service.MovieService;
import com.next.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自启动类，继承 ApplicationRunner 运行前自启动
 * 将 movie 缓存到 redis 中。
 */

@Component
public class AutoBootRunner implements ApplicationRunner {
    @Autowired
    private MovieService movieService;
    @Autowired
    private RedisOperator redisOperator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1. 取出全部的 movie
        List<Movie> movies = movieService.queryAllMovies();
        // 2. 存入 Redis 中
        for (int i = 0; i < movies.size(); i++) {
            redisOperator.set("guess-trailer-id:" + i, JsonUtils.objectToJson(movies.get(i)));
        }
    }
}
