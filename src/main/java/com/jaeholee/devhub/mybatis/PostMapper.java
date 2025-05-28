package com.jaeholee.devhub.mybatis;

import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.domain.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    Post getPostById(int id);

    List<Post> getAllPosts();

    void insertPost(Post post);

    void deletePostById(int id);

    void insertReply(Reply reply);

    void deleteReplyById(int id);

    List<Reply> getRepliesByPostId(int post_id);
}
