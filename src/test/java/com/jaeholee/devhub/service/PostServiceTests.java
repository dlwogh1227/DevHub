package com.jaeholee.devhub.service;

import com.jaeholee.devhub.DevHubApplication;
import com.jaeholee.devhub.domain.Post;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

}
