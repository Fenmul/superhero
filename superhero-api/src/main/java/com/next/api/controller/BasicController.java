package com.next.api.controller;

import com.next.api.config.FaceConfig;
import com.next.pojo.Users;
import com.next.pojo.vo.UsersVO;
import com.next.redis.RedisOperator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BasicController {

    @Autowired
    protected RedisOperator redis;

    @Autowired
    protected FaceConfig faceConfig;

    // 用户登录 token
    public final static String REDIS_UNIQUE_TOKEN = "redis-unique-token";

    /**
     * 设置 token ，并返回 userVO 对象
     * @param user 用户信息
     * @return userVO
     */
    public UsersVO setToken(Users user){
        if (user == null || StringUtils.isBlank(user.getId())) {
            return null;
        }
        // 会话建立前后端的联系
        // 实现用户分布式会话，创建一个 Token 保存在 redis 中，可以被任意集群节点访问
        String uniqueToken = UUID.randomUUID().toString().trim();
        redis.set(REDIS_UNIQUE_TOKEN +":"+user.getId(), uniqueToken);
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(user, usersVO);
        usersVO.setUserUniqueToken(uniqueToken);
        return usersVO;
    }
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
