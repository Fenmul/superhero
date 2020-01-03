package com.next.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.next.mapper.MovieMapper;
import com.next.pojo.Movie;
import com.next.service.MovieService;
import com.next.utils.JqGridResult;
import com.next.utils.MovieTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryAllTrailerCounts() {
        // 查询所有的电影，直接传入空对象即可
        return movieMapper.selectCount(new Movie());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Movie> queryAllMovies() {
        return movieMapper.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public JqGridResult searchTrailer(String keywords, Integer page, Integer pageSize) {
        Example example = new Example(Movie.class);
        example.orderBy("createTime").desc();
        // 此处区分中文和英文
        Example.Criteria criteria = example.createCriteria();
        // 重点： 模糊查询需要在查询条件加上 %, 使用 OR 关联
        criteria.orLike("name", "%"+keywords+"%").orLike("originalName", "%"+keywords+"%");

        PageHelper.startPage(page, pageSize);
        List<Movie> movies = movieMapper.selectByExample(example);
        // 转换成 page 对象
        PageInfo<Movie> pagedMovies = new PageInfo<>(movies);
        JqGridResult jqGridResult = new JqGridResult();
        jqGridResult.setPage(page);
        jqGridResult.setRows(movies);
        // 总页数
        jqGridResult.setTotal(pagedMovies.getPages());
        // 总记录数
        jqGridResult.setRecords(pagedMovies.getTotal());
        return jqGridResult;
    }
}
