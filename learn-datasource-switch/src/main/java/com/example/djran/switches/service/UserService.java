package com.example.djran.switches.service;

import com.example.djran.switches.model.Posts;
import com.example.djran.switches.model.Users;

import java.util.List;

/**
 * @author
 * 用户服务接口
 */
public interface UserService {

    List<Users> getAllUsers(String type);

    int savePosts(String type);

    List<Posts> getPost(String type);

    List<Posts> getPostFromLastDB();
}
