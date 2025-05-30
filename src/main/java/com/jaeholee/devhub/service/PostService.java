package com.jaeholee.devhub.service;

import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.domain.Reply;
import com.jaeholee.devhub.dto.PostWithUsername;
import com.jaeholee.devhub.dto.RepliesWithUsername;
import com.jaeholee.devhub.mybatis.PostMapper;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostService {

    private final PostMapper postMapper;

    public Post getPostById(int id) {
        return postMapper.getPostById(id);
    }

    public List<PostWithUsername> getAllPosts() {
        return postMapper.getAllPosts();
    }

    public void insertPost(Post post) {
        postMapper.insertPost(post);
    }

    public void deletePostById(int id) {
        postMapper.deletePostById(id);
    }

    public void insertReply(Reply reply) {
        postMapper.insertReply(reply);
    }

    public void deleteReply(int id) {
        postMapper.deleteReplyById(id);
    }

    public List<RepliesWithUsername> getRepliesByPostId(int post_id){
        return postMapper.getRepliesByPostId(post_id);
    }
}
