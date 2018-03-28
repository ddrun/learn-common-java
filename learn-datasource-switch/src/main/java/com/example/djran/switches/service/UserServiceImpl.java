package com.example.djran.switches.service;

import com.example.djran.switches.config.DynamicDataSourceContextHolder;
import com.example.djran.switches.model.Users;
import com.example.djran.switches.model.Posts;
import com.example.djran.switches.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 查询用户
     *读写分离，使用备用数据源读取数据
     * @return
     */
    @Override
    public List<Users> getAllUsers(String type) {
        if("oracle".equals(type)){
            DynamicDataSourceContextHolder.setDataSourceType("ORACLE");
        }
        if("mysql".equals(type)){
            DynamicDataSourceContextHolder.setDataSourceType("MYSQL");
        }
        if("dengjr".equals(type)){
            DynamicDataSourceContextHolder.setDataSourceType("DENGJR");
        }
        return usersMapper.getAllUser();
    }

    /**
     * 保存用户信息
     * @return
     * 读写分离，使用主数据源读写数据
     */
    @Override
    public int savePosts(String type) {
        if("mysql".equals(type)){
            DynamicDataSourceContextHolder.setDataSourceType("MYSQL");
        }
        Posts posts = new Posts();
        posts.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        posts.setTitle("数据源切换测试:"+date2String("yyyy-MM-dd HH:mm:ss",new Date()));
        posts.setCreateTime(new Date());
        usersMapper.savePosts(posts);
        return 1;
    }

    @Override
    public List<Posts> getPost(String type) {
        if("dengjr".equals(type)){
            DynamicDataSourceContextHolder.setDataSourceType("DENGJR");
        }
        return usersMapper.getAllPost();
    }

    @Override
    public List<Posts> getPostFromLastDB() {
        return usersMapper.getAllPost();
    }

    public String date2String(String formatString, Date targetDate) {
        SimpleDateFormat format = null;
        String result = null;
        if (targetDate != null) {
            format = new SimpleDateFormat(formatString);
            result = format.format(targetDate);
        } else {
            return null;
        }
        return result;
    }

}
