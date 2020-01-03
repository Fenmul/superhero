package com.next.api.controller;

import com.next.redis.RedisOperator;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    protected RedisOperator redis;

    /**
     * 返回 size 个 count 内的随机整数
     * @param count 总数
     * @param size 数目
     * @return 数组
     */
    public Integer[] guessULikeArray(Integer count, Integer size){
        Integer[] guessULikeArray = new Integer[size];
        for (int i = 0; i < guessULikeArray.length; i++) {
            // 生成随机数
            Integer guess = (int)(Math.random()*count);
            // 去除重复项
            if (ArrayUtils.contains(guessULikeArray, guess)) {
                i--;
                continue;
            }
            guessULikeArray[i] = guess;
        }
        return guessULikeArray;
    }
}
