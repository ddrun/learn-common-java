package com.example.djran.switches.mapper;

import com.example.djran.switches.model.Users;
import com.example.djran.switches.model.Posts;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 */
@Repository
public interface UsersMapper {

    @Select("SELECT * FROM SYSUSER")
    List<Users> getAllUser();

    @Insert("insert into C_POSTS(ID,TITLE,CREATE_TIME)values (#{id},#{title},#{createTime})")
    int savePosts(Posts posts);

    @Select("select * from C_POSTS where ID='ab3bbc9361844b909b1ff400f14aae9d'")
    List<Posts> getAllPost();
}
