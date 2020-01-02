package com.next.service.impl;

import com.github.pagehelper.PageHelper;
import com.next.mapper.MovieMapper;
import com.next.pojo.Movie;
import com.next.service.MovieService;
import com.next.utils.MovieTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> queryHotSuperHero(String type) {
        // 分页只取第一页
        Integer page = 1;
        Integer pageSize = 0;
        Example example = new Example(Movie.class);
        if (StringUtils.equals(type, MovieTypeEnum.SUPERHERO.getType())) {
            // 热门超英按照评分排序
            example.orderBy("score").desc();
            pageSize = 8;
        } else if (StringUtils.equals(type, MovieTypeEnum.TRAILER.getType())) {
            // 热门预告按照点赞排序
            example.orderBy("prisedCounts").desc();
            pageSize = 4;
        } else {
            return null;
        }
        // 设置分页
        PageHelper.startPage(page, pageSize);
        return movieMapper.selectByExample(example);
    }
}
