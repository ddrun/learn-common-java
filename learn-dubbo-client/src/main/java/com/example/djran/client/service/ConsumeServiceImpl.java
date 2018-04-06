package com.example.djran.client.service;

import com.example.djran.api.model.Posts;
import com.example.djran.api.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2018/4/4
 *
 * @author dengjr
 */
@Service
public class ConsumeServiceImpl implements ConsumeService{

    @Autowired
    private PostsService postsService;

    @Override
    public List<Posts> queryPostList(){
        return postsService.queryPostsList();
    }
}
