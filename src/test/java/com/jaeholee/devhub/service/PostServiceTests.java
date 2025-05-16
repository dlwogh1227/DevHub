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
        System.out.println(t.get(0));
    }

    @Test
    public void initializeDB(){
        String path = "C:\\Users\\KDT-25\\Desktop\\gallery";

        String image_folder = path + "\\image";
        String imageThumb_folder = path + "\\imageThumbnail";
        String video_folder = path + "\\video";
        String videoThumb_folder = path + "\\videoThumbnail";

        File image_file = new File(image_folder);
        File video_file = new File(video_folder);

        for (File file : Objects.requireNonNull(image_file.listFiles())) {
            Post post = Post.builder().title("세부")
                    .path(image_folder+"\\"+file.getName())
                    .thumbnail_path(imageThumb_folder+"\\"+file.getName()+"_thumbnail")
                    .content_type("IMAGE")
                    .creator("leejaeho")
                    .build();
            postService.insertPost(post);
        }
        for (File file : Objects.requireNonNull(video_file.listFiles())) {
            Post post = Post.builder().title("세부")
                    .path(image_folder+"\\"+file.getName())
                    .thumbnail_path(videoThumb_folder+"\\"+file.getName()+"_thumbnail")
                    .content_type("VIDEO")
                    .creator("leejaeho")
                    .build();
            postService.insertPost(post);
        }
    }
}
