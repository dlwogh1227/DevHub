package com.jaeholee.devhub.controller;

import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.service.PostService;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String read(){
        return "gallery/read";
    }
}
