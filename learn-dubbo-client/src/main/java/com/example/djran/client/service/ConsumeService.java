package com.example.djran.client.service;

import com.example.djran.api.model.Posts;

import java.util.List;

/**
 * Created on 2018/4/4
 *
 * @author d.djran@gmail.com
 */
public interface ConsumeService {
    List<Posts> queryPostList();
}
