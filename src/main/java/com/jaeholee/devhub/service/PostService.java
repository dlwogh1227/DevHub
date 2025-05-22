package com.jaeholee.devhub.service;

import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.domain.Reply;
import com.jaeholee.devhub.mybatis.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    public Post getPostById(int id) {
        return postMapper.getPostById(id);
    }

    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    public void insertPost(Post post) {
        postMapper.insertPost(post);
    }

    public void insertReply(Reply reply) {
        postMapper.insertReply(reply);
    }

    public void deleteReply(int id) {
        postMapper.deleteReplyById(id);
    }

    public List<Reply> getRepliesByPostId(int post_id){
        return postMapper.getRepliesByPostId(post_id);
    }
}
