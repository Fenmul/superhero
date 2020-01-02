package com.next.api.controller;


import com.next.service.CarouselService;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="首页", tags={"首页展示的相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @ApiOperation(value="获取首页轮播图列表", notes="获取首页轮播图列表", httpMethod = "POST")
    @GetMapping("/carousel/list")
    public NEXTJSONResult queryAll(){
        return NEXTJSONResult.ok(carouselService.queryAll());
    }

}
