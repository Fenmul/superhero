package com.next.controller;


import com.next.service.CarouselService;
import com.next.utils.NEXTJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @GetMapping("/carousel/list")
    public NEXTJSONResult queryAll(){
        return NEXTJSONResult.ok(carouselService.queryAll());
    }

}
