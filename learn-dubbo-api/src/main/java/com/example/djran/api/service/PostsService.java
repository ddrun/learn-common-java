package com.example.djran.api.service;

import com.example.djran.api.model.Posts;

import java.util.List;

/**
 * Created on 2018/3/29
 * 内容接口
 * @author dengjr
 */
public interface PostsService {
    /**
     * 查询post列表
     * @return
     */
    List<Posts> queryPostsList();

    /**
     * 更新posts
     * @param posts
     * @return
     */
    int updatePosts(Posts posts);
}
