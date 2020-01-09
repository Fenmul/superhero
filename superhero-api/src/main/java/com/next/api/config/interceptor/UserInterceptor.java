package com.next.api.config.interceptor;

import com.next.redis.RedisOperator;
import com.next.utils.JsonUtils;
import com.next.utils.NEXTJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 工作流接口，允许自定义处理程序执行链。
 * 拦截器的使用场景：
 * 1、日志记录，可以记录请求信息的日志，以便进行信息监控、信息统计等。
 * 2、权限检查：如登陆检测，进入处理器检测是否登陆，如果没有直接返回到登陆页面。
 * 3、性能监控：典型的是慢日志。
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redisOperator;
    public final static String REDIS_UNIQUE_TOKEN = "redis-unique-token";

    /**
     * 拦截请求，在controller调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 Header 获取到对应的
        String userId = request.getHeader("headerUserId");
        String userToken = request.getHeader("headerUserToken");

        if (StringUtils.isBlank(userId)) {
            returnErrorResponse(response, NEXTJSONResult.errorTokenMsg("请登录..."));
        } else if (StringUtils.isBlank(userToken)){
            returnErrorResponse(response, NEXTJSONResult.errorTokenMsg("用户会话已经失效，请重新登录。"));
        } else {
            String token = redisOperator.get(REDIS_UNIQUE_TOKEN);
            if (StringUtils.equals(token, userToken)) {
                return true;
            } else {
                returnErrorResponse(response, NEXTJSONResult.errorTokenMsg("用户异地登录"));
            }
        }
        return false;
    }

    /**
     * 构建一个返回json的方法
     */
    public void returnErrorResponse(HttpServletResponse response,
                                    NEXTJSONResult result) throws Exception {
        OutputStream out = null;
        try {
            // 设置字符集
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 拦截请求，在controller调用之后，视图渲染前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 拦截请求，视图渲染后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
