package com.next.api.config;

import com.next.pojo.Movie;
import com.next.redis.RedisOperator;
import com.next.service.MovieService;
import com.next.utils.DateUtil;
import com.next.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
            Movie movie = movies.get(i);
            redisOperator.set("guess-trailer-id:" + i, JsonUtils.objectToJson(movie));

            // 按照时间顺序将电影存入到 redis
            String createTimeStr = DateUtil.dateToString(movie.getCreateTime(), DateUtil.ISO_EXPANDED_DATE_FORMAT);
            String movieKey = "movie:" + createTimeStr;
            // 此处注意，同一天可能有多部电影上映，所以获取到的是 list 类型
            String movieListStr = redisOperator.get(movieKey);
            if (StringUtils.isNotBlank(movieListStr)) {
                List<Movie> movieList = JsonUtils.jsonToList(movieListStr, Movie.class);
                if (movieList != null && movieList.size() >0 ){
                    // 只有当电影不存在时才会将电影存放到 redis 中
                    boolean isExist = true;
                    for (Movie mov : movieList) {
                        if (mov.getId().equals(movie.getId())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        movieList.add(movie);
                        redisOperator.set(movieKey, JsonUtils.objectToJson(movieList));
                    } else {
                        continue;
                    }
                }

            }
            // redis 中不存在，则直接插入
            redisOperator.set(movieKey, JsonUtils.objectToJson(Collections.singleton(movie)));
        }


    }
}
