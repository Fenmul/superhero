package com.next.api.controller;

import com.next.service.MovieService;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="搜索", tags={"搜索页相关接口"})
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private MovieService movieService;

    @ApiOperation(value="热门超英/预告片", notes="获取热门超英/预告片列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "查询的名称，中文名/英文名", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "查询的下一个页面的页数，第[page]页", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页的每一页显示的条数", dataType = "integer", paramType = "query")
    })
    @PostMapping("/list")
    public Object searchTrailer (String keywords, Integer page, Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            keywords = "";
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 9;
        }
        return NEXTJSONResult.ok(movieService.searchTrailer(keywords,page,pageSize));
    }

}
