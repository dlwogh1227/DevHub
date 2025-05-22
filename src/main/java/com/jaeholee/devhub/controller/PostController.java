package com.jaeholee.devhub.controller;

import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.domain.Reply;
import com.jaeholee.devhub.service.PostService;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Post> list = postService.getAllPosts();
        model.addAttribute("posts", list);
        return "gallery/list";
    }

    @GetMapping("/read")
    public String read(int id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "gallery/read";
    }

    @PostMapping("/add_reply")
    @ResponseBody
    public List<Reply> addReply(int postId, String content) {
        Reply reply = new Reply();
        reply.setPost_id(postId);
        reply.setBody(content);
        reply.setCreator("leejaeho");
        postService.insertReply(reply);
        return postService.getRepliesByPostId(postId);
    }

    @PostMapping("/remove_reply")
    @ResponseBody
    public List<Reply> removeReply(int replyId, int postId) {
        postService.deleteReply(replyId);
        return postService.getRepliesByPostId(postId);
    }

}
