package com.example.djran.server.dao;

import com.example.djran.api.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/3/29
 *
 * @author d.djran@gmail.com
 */
@Repository
public interface PostsDao extends JpaRepository<Posts,String>{
}
