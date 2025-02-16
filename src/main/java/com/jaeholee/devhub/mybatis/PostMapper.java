package com.jaeholee.devhub.mybatis;

import com.jaeholee.devhub.domain.Attachment;
import com.jaeholee.devhub.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(Post post);

    void insertAttachment(Attachment attachment);

    List<Post> selectAllPosts();

    List<Attachment> selectAttachmentsByPostId(long id);
}
