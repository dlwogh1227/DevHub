package com.jaeholee.devhub.mybatis;

import com.jaeholee.devhub.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);

    void insertUser(User user);
}
