package com.example.djran.switches.controller;

import com.example.djran.switches.model.Posts;
import com.example.djran.switches.model.Users;
import com.example.djran.switches.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAllUsers")
    public List<Users> getUsers(String type) {
        return userService.getAllUsers(type);
    }

    @RequestMapping("/savePost")
    public int savePosts(String type) {
        return userService.savePosts(type);
    }
    @RequestMapping("/getPost_LastDB")
    public List<Posts> getPostFromLastDB() {
        return userService.getPostFromLastDB();
    }

    @RequestMapping("/getPost")
    public List<Posts> getPost(String type) {
        return userService.getPost(type);
    }
}
