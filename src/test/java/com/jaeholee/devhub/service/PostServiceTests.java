package com.jaeholee.devhub.service;

import com.jaeholee.devhub.domain.Post;
import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@Log4j2
public class PostServiceTests {

    @Autowired
    PostService postService;

    @Test
    public void test() {
        List<Post> t = postService.getAllPosts();
        for (Post p : t) {
            System.out.println(p);
        }
    }

    @Test
    public void initializeDB(){
        String path = "C:\\Users\\KDT-25\\Desktop\\gallery\\";

        String image_folder = "image";
        String imageThumb_folder = "imageThumbnail";
        String video_folder = "video";
        String videoThumb_folder = "videoThumbnail";

        File image_file = new File(path + image_folder);
        File video_file = new File(path + video_folder);

        for (File file : Objects.requireNonNull(image_file.listFiles())) {
            Post post = new Post();
            post.setTitle("세부");
            post.setPath("/"+image_folder+"/"+file.getName());
            String[] file_sp = file.getName().split("\\.");
            post.setThumbnail_path("/"+imageThumb_folder+"/"+file_sp[0]+"_thumbnail.jpg");
            post.setContent_type("IMAGE");
            post.setCreator("leejaeho");
            postService.insertPost(post);
        }
        for (File file : Objects.requireNonNull(video_file.listFiles())) {
            Post post = new Post();
            post.setTitle("세부");
            post.setPath("/"+video_folder+"/"+file.getName());
            String[] file_sp = file.getName().split("\\.");
            post.setThumbnail_path("/"+videoThumb_folder+"/"+file_sp[0]+"_thumbnail.jpg");
            post.setContent_type("VIDEO");
            post.setCreator("leejaeho");
            postService.insertPost(post);
        }
    }
}
