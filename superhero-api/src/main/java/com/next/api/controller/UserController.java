package com.next.api.controller;


import com.next.api.config.FaceConfig;
import com.next.pojo.Users;
import com.next.pojo.bo.RegistLoginUsersBO;
import com.next.pojo.vo.UsersVO;
import com.next.service.UserService;
import com.next.utils.DateUtil;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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


    @ApiOperation(value="用户注册/登录", notes="用户注册或者用户登录的统一入口", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "registLoginUsersBO", value = "用户对象", required = true, dataType = "RegistLoginUsersBO", paramType = "body")})
    @PostMapping("/registOrLogin")
    public NEXTJSONResult registOrLogin(@RequestBody RegistLoginUsersBO registLoginUsersBO) throws Exception {
        if (StringUtils.isAnyBlank(registLoginUsersBO.getPassword(), registLoginUsersBO.getUsername())) {
            NEXTJSONResult.errorMsg("用户信息均不能为空");
        }
        Users user;
        boolean userIsExists = userService.queryUserIsExists(registLoginUsersBO.getUsername());
        if (userIsExists) {
            user = userService.loginRegister(registLoginUsersBO);
        } else {
            user = userService.saveUserRegister(registLoginUsersBO);
        }
        return NEXTJSONResult.ok(setToken(user));
    }

    @ApiOperation(value="用户上传头像", notes="用户上传头像", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户主键id", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "file", value = "用户头像", required = true, dataType = "MultipartFile", paramType = "body"),
    })
    @PostMapping("/uploadFace")
    public NEXTJSONResult uploadFace(@RequestParam("userId") String userId, @RequestBody MultipartFile file){
        if (StringUtils.isBlank(userId) || null == file) {
            NEXTJSONResult.errorMsg("用户 ID 和头像图片均不能为空");
        }

        // 文件输出到服务器「这边就是我的电脑本机」
        FileOutputStream fileOutputStream = null;
        // 用户接收 file 中的流
        InputStream inputStream = null;

        // 保存到服务器的文件目录：/Users/fox/Documents/shfile/
        String filePrefix = faceConfig.getFileSpace() + File.separator + userId;

        // 获取上传的文件后缀名
        String originalFilename = file.getOriginalFilename();
        String[] fileNameArray = originalFilename.split("\\.");
        String fileSuffix = fileNameArray[fileNameArray.length - 1];

        String fileName ="face-" + userId + "." + fileSuffix;
        // 创建在服务器端要上传文件地址的 File 类
        File outFile = new File(filePrefix + File.separator + fileName);
        // 判断要上传的文件父类目录不为空，并且目前不是文件夹「创建之后才是文件夹」
        if (outFile.getParentFile() != null && !outFile.getParentFile().isDirectory()) {
            outFile.getParentFile().mkdirs();
        }

        try {
            // 拷贝文件到输出文件对象
            fileOutputStream = new FileOutputStream(outFile);
            inputStream = file.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 将上传的文件路径保存在数据库中
        Users user = new Users();
        user.setId(userId);
        String fileUrl = faceConfig.getUrl() + "/" + userId + "/" + fileName;
        // 为了防止前端缓存加上时间戳
        fileUrl += ("?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN));
        user.setFaceImage(fileUrl);

        Users resultUser = userService.updateUserInfo(user);
        resultUser.setPassword(null);
        return NEXTJSONResult.ok(conventUsersVO(resultUser));
    }

    private UsersVO conventUsersVO(Users user) {
        String token = redis.get(REDIS_UNIQUE_TOKEN + ":" + user.getId());
        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setUserUniqueToken(token);
        return userVO;
    }
}
