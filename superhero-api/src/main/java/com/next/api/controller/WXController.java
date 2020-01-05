package com.next.api.controller;


import com.google.common.collect.Maps;
import com.next.api.config.WXConfig;
import com.next.pojo.Users;
import com.next.pojo.bo.MPWXUserBO;
import com.next.pojo.bo.WXSessionBO;
import com.next.pojo.vo.UsersVO;
import com.next.service.UserService;
import com.next.utils.HttpClientUtils;
import com.next.utils.JsonUtils;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@Api(value="微信相关控制器", tags={"微信小程序登录"})
@RestController
public class WXController extends BasicController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="微信小程序登录", notes="根据code进行用户的登录与注册", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "通过wx.login获得的code\t", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "mpwxUserBO", value = "超英(superhero)/预告(trailer)", required = true, dataType = "MPWXUserBO", paramType = "body")
    })
    @PostMapping("/mpWXLogin/{code}")
    public NEXTJSONResult mpWXLogin(
            @PathVariable("code") String code,
            @RequestBody MPWXUserBO mpwxUserBO){
        if (StringUtils.isBlank(code)) {
            NEXTJSONResult.errorMsg("参数不能为空");
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap<String, String> param = Maps.newHashMap();
        param.put("appid", WXConfig.appId);
        param.put("secret", WXConfig.secret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String wxResult = HttpClientUtils.doGet(url, param);
        WXSessionBO wxSessionBO = JsonUtils.jsonToPojo(wxResult, WXSessionBO.class);
        // 1. 查询是否已经注册
        Users user = userService.queryUserLoginByMPWX(wxSessionBO.getOpenid());
        // 2. 未注册则注册用户
        if (user == null) {
            user = userService.saveUserMPWX(wxSessionBO.getOpenid(), mpwxUserBO);
        }

        return NEXTJSONResult.ok(setToken(user));
    }

}
