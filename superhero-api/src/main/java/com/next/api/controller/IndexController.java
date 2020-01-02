package com.next.api.controller;


import com.next.service.CarouselService;
import com.next.service.MovieService;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value="首页", tags={"首页展示的相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController extends BasicController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private MovieService movieService;

    // 前端需要 POST请求
    @ApiOperation(value="获取首页轮播图列表", notes="获取首页轮播图列表", httpMethod = "POST")
    @PostMapping("/carousel/list")
    public NEXTJSONResult queryAll(){
        return NEXTJSONResult.ok(carouselService.queryAll());
    }

    @ApiOperation(value="猜你喜欢", notes="随机获得预告片的列表", httpMethod = "POST")
    @PostMapping("/guessULike")
    public NEXTJSONResult guessULike(){
        // 1. 查询出电影总数
        Integer counts = movieService.queryAllTrailerCounts();
        // 2. 取出 5 个随机数
        Integer[] randoms = guessULikeArray(counts, 5);
        // 从 Redis 中取出对应的 movie 对象
        return NEXTJSONResult.ok(randoms);
    }

    @ApiOperation(value="热门超英/预告片", notes="获取热门超英/预告片列表", httpMethod = "GET")
    @ApiImplicitParam(name = "type", value = "超英(superhero)/预告(trailer)", required = true, dataType = "string", paramType = "query")
    @PostMapping("/movie/hot")
    public NEXTJSONResult queryHotSuperHero(@RequestParam("type") String type){
        if (StringUtils.isBlank(type)) {
            NEXTJSONResult.errorMsg("参数不能为空");
        }
        return NEXTJSONResult.ok(movieService.queryHotSuperHero(type));
    }

}
