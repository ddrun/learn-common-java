package com.example.djran.server.service;

import com.example.djran.api.model.Posts;
import com.example.djran.api.service.PostsService;
import com.example.djran.server.dao.PostsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2018/3/29
 * Posts接口实现
 * @author d.djran@gmail.com
 */
@Service()
public class PostsServiceImpl implements PostsService{

    @Autowired
    private PostsDao postsDao;
    @Override
    public List<Posts> queryPostsList() {
        return postsDao.findAll();
    }

    @Override
    public int updatePosts(Posts posts) {
        return 0;
    }
}
