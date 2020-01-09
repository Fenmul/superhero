package com.next.api.config;

import com.next.pojo.Movie;
import com.next.redis.RedisOperator;
import com.next.utils.DateUtil;
import com.next.utils.JsonUtils;
import com.next.utils.MoviePushUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 电影推送类
 */
@Component
public class MovieJob {

    @Autowired
    private RedisOperator redisOperator;

    // 0/5 * * * * ?  每隔 5 秒钟执行一次
    // 0 0 1 * * ? *  每天 1 点执行一次
    @Scheduled(cron = "0/5 * * * * ? ")
    public void timePush(){

        // 从 redis 中取出对应的数据，进行推送
        Date tomorrow = DateUtil.dateIncreaseByDay(DateUtil.getCurrentDateTime(), 1);
        String movieListStr = redisOperator.get("movie:" + DateUtil.dateToString(tomorrow, DateUtil.ISO_EXPANDED_DATE_FORMAT));
        String title = "电影速递";
        String text = "";
        if (StringUtils.isNotBlank(movieListStr)) {
            List<Movie> movieList = JsonUtils.jsonToList(movieListStr, Movie.class);
            if (movieList != null && movieList.size() > 0) {
                for (Movie movie : movieList) {
                    text += ("「" + movie.getName() +"」");
                }
            }
        }
        if (StringUtils.isNotBlank(text)) {
            text += "将于明日上映";
            MoviePushUtil.doPush(title, text);
        }
    }
}
