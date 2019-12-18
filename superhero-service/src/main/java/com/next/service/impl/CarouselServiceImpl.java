package com.next.service.impl;

import com.next.mapper.CarouselMapper;
import com.next.pojo.Carousel;
import com.next.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll() {
        Example example = new Example(Carousel.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", 1);
        // 倒序
        example.orderBy("sort").desc();
        return carouselMapper.selectByExample(example);
    }

}
