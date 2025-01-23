package com.jaeholee.devhub;

import com.jaeholee.devhub.mybatis.PostMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DevHubApplicationTests {

    @Autowired
    PostMapper postMapper;

    @Test
    void contextLoads() {
        System.out.println("fdsfsdfdsfsdf     "+postMapper.test());
    }

}
