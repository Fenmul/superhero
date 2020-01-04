package com.next.api.controller;


import com.next.service.UserService;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="用户相关", tags={"用户相关接口"})
@RestController
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户退出登录", notes="执行用户退出登录的操作接口", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户主键id", required = true, dataType = "string", paramType = "query")})
    @PostMapping("/logout")
    public NEXTJSONResult logout(@RequestParam("userId") String userId){
        if (StringUtils.isBlank(userId)) {
            NEXTJSONResult.errorMsg("参数不能为空");
        }

        // 登出就是清除会话，断开前后端的联系
        redis.del(REDIS_UNIQUE_TOKEN + ":" + userId);
        return NEXTJSONResult.ok();
    }

}
