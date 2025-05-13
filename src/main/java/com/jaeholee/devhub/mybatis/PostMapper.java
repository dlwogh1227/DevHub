package com.jaeholee.devhub.mybatis;

import com.jaeholee.devhub.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    Post getPostById(int id);

    List<Post> getAllPosts();

    void insertPost(Post post);
}
