package com.jaeholee.devhub.controller;

import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.domain.Reply;
import com.jaeholee.devhub.service.FileUploadService;
import com.jaeholee.devhub.service.PostService;
import com.jaeholee.devhub.service.ThumbnailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Log4j2
@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileUploadService fileUploadService;
    private final ThumbnailService thumbnailService;

    @Value("${upload.path}")
    private String content_path;

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

    @PostMapping("/upload_post")
    public String uploadPost(MultipartFile file, String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be empty");
        }
        String content_type = getFileType(file);
        Post post = new Post();
        post.setTitle(title);
        post.setContent_type(content_type);
        post.setCreator("익명");
        if (content_type.equals("IMAGE")){
            String filename = fileUploadService.saveFile(file, content_path + "\\image\\");
            String thumb_name = getThumbnailName(filename);
            thumbnailService.createImageThumbnail(content_path + "\\image\\" + filename, content_path + "\\imageThumbnail\\" + thumb_name);
            post.setPath("/image/" + filename);
            post.setThumbnail_path("/imageThumbnail/" + thumb_name);
        } else if (content_type.equals("VIDEO")) {
            String filename = fileUploadService.saveFile(file, content_path + "\\video\\");
            String thumb_name = getThumbnailName(filename);
            thumbnailService.createVideoThumbnail(content_path + "\\video\\" + filename, content_path + "\\videoThumbnail\\" + thumb_name);
            post.setPath("/video/" + filename);
            post.setThumbnail_path("/videoThumbnail/" + thumb_name);
        }
        try{
            postService.insertPost(post);
        } catch (Exception e) {
            try{
                fileUploadService.deleteFile(content_path + changeSlash(post.getPath()));
                fileUploadService.deleteFile(content_path + changeSlash(post.getThumbnail_path()));// 수정 필요@@@
            } catch (IOException ie){
                log.error(ie);
            }
        }

        return "redirect:/gallery/list";
    }

    public String changeSlash(String path) {
        return path.replace('/', '\\');
    }

    public String getThumbnailName(String filename) {
        String[] split = filename.split("\\.");
        if (split.length > 2) {
            throw new IllegalArgumentException("filename must contain only one dot");
        }
        return split[0] + "_thumbnail." + split[1];
    }

    public String getFileType(MultipartFile file) {
        if (file == null || file.isEmpty()) throw new IllegalArgumentException("반드시 파일이 존재해야 합니다");;

        String contentType = file.getContentType();
        String filename = file.getOriginalFilename();

        if (contentType == null || filename == null || !filename.contains(".")) throw new IllegalArgumentException("파일 이름, 확장자 또는 콘텐츠의 타입을 확인하십시오");;

        String ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        // MIME 기반 확인
        if (isImageMime(contentType) && isImageExtension(ext)) return "IMAGE";
        if (isVideoMime(contentType) && isVideoExtension(ext)) return "VIDEO";

        throw new IllegalArgumentException("허용되는 파일 형식이 아닙니다");
    }

    private boolean isImageExtension(String ext) {
        return ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png");
    }

    private boolean isVideoExtension(String ext) {
        return ext.equals("mp4");
    }

    private boolean isImageMime(String mime) {
        return mime.startsWith("image/");
    }

    private boolean isVideoMime(String mime) {
        return mime.startsWith("video/");
    }

}
