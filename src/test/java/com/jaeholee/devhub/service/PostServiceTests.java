package com.jaeholee.devhub.service;

import com.jaeholee.devhub.DevHubApplication;
import com.jaeholee.devhub.domain.Attachment;
import com.jaeholee.devhub.domain.AttachmentType;
import com.jaeholee.devhub.domain.Post;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;

@SpringBootTest
@ContextConfiguration(classes = DevHubApplication.class)
@Log4j2
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    public void postTest() {
        postService.initializeDB();
    }

}
