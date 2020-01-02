package com.next.api.controller;


import com.next.service.CarouselService;
import com.next.service.MovieService;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value="首页", tags={"首页展示的相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private MovieService movieService;

    @ApiOperation(value="获取首页轮播图列表", notes="获取首页轮播图列表", httpMethod = "POST")
    @GetMapping("/carousel/list")
    public NEXTJSONResult queryAll(){
        return NEXTJSONResult.ok(carouselService.queryAll());
    }

    @ApiOperation(value="热门超英/预告片", notes="获取热门超英/预告片列表", httpMethod = "GET")
    @ApiImplicitParam(name = "type", value = "超英(superhero)/预告(trailer)", required = true, dataType = "string", paramType = "query")
    @GetMapping("/movie/hot")
    public NEXTJSONResult queryHotSuperHero(@RequestParam("type") String type){
        if (StringUtils.isBlank(type)) {
            NEXTJSONResult.errorMsg("参数不能为空");
        }
        return NEXTJSONResult.ok(movieService.queryHotSuperHero(type));
    }

}