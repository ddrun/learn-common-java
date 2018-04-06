package com.example.djran.client.controller;

import com.example.djran.api.model.Posts;
import com.example.djran.client.service.ConsumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2018/4/4
 *
 * @author dengjr
 */
@RestController
@RequestMapping("test")
public class ConsumeController {
    @Autowired
    private ConsumeServiceImpl consumeService;
    @RequestMapping("client")
    public List<Posts> queryPostList(){
        List<Posts> list=consumeService.queryPostList();
        return list;
    }
}
