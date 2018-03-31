package com.example.djran.server.controller;

import com.example.djran.api.model.Posts;
import com.example.djran.common.dto.DataRet;
import com.example.djran.server.service.PostsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2018/3/29
 *
 * @author d.djran@gmail.com
 */
@RestController
@RequestMapping("post")
public class PostsController {
    @Autowired
    private PostsServiceImpl postsService;
    @GetMapping("/list")
    public DataRet<List<Posts>> queryPosts(){
        List<Posts> postsList=postsService.queryPostsList();
        return new DataRet<>(postsList);
    }
}
