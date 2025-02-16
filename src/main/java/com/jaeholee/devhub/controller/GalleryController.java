package com.jaeholee.devhub.controller;

import com.jaeholee.devhub.domain.Attachment;
import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
@Log4j2
public class GalleryController {

    private final PostService postService;

    @GetMapping("/list")
    public void list(Model model) {
        List<Post> postList = postService.selectAllPosts();
        model.addAttribute("postList", postList);
    }

    @GetMapping("/read")
    public void read(long id ,Model model) {
        List<Attachment> attachments = postService.selectAttachmentsByPostId(id);
        log.info(attachments);
        model.addAttribute("attachments", attachments);
    }
}
