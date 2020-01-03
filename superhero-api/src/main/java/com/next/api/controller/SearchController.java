package com.next.api.controller;

import com.next.mapper.StaffMapperCustom;
import com.next.service.MovieService;
import com.next.service.StaffService;
import com.next.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="搜索", tags={"搜索页相关接口"})
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private StaffService staffService;

    @ApiOperation(value="热门超英/预告片", notes="获取热门超英/预告片列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "查询的名称，中文名/英文名", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "查询的下一个页面的页数，第[page]页", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页的每一页显示的条数", dataType = "integer", paramType = "query")
    })
    @PostMapping("/list")
    public NEXTJSONResult searchTrailer(String keywords, Integer page, Integer pageSize) {
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

    @ApiOperation(value="预告片详情", notes="根据编号id查询预告片的详细信息", httpMethod = "POST")
    @ApiImplicitParam(name = "trailerId", value = "预告片的主键id", dataType = "string", paramType = "path", required = true)
    @PostMapping("/trailer/{trailerId}")
    public NEXTJSONResult searchTrailerDetail(@PathVariable("trailerId") String trailerId) {
        if (StringUtils.isBlank(trailerId)) {
            NEXTJSONResult.errorMsg("未传入有效查询条件");
        }
        return NEXTJSONResult.ok(movieService.queryMovieById(trailerId));
    }

    // 详情页面拆分，防止页面加载慢，导致整个页面无法打开，拆分开来至少可以显示一部分内容
    @ApiOperation(value="查询演职人员", notes="根据电影的id以及演职角色查询导演或者演员", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trailerId", value = "预告片的主键id", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(name = "role", value = "角色[1:导演][2:演员]", dataType = "integer", paramType = "path", required = true)
    })
    @PostMapping("/staff/{trailerId}/{role}")
    public NEXTJSONResult searchStaff(@PathVariable("trailerId") String trailerId, @PathVariable("role") Integer role) {
        if (StringUtils.isBlank(trailerId) || role == null) {
            NEXTJSONResult.errorMsg("未传入有效查询条件");
        }
        return NEXTJSONResult.ok(staffService.queryStaffs(trailerId, role));
    }
}
