package com.jaeholee.devhub.service;

import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.dto.PostWithUsername;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
@Log4j2
public class PostServiceTests {

    @Autowired
    PostService postService;

    @Test
    public void test() {
        List<PostWithUsername> t = postService.getAllPosts();
        for (PostWithUsername p : t) {
            System.out.println(p);
        }
    }

    @Test
    public void initializeDB(){
        String path = "C:\\Users\\KDT-25\\Desktop\\temp\\";

        File files = new File(path);
    }
}
