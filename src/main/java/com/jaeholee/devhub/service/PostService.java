package com.jaeholee.devhub.service;

import com.jaeholee.devhub.domain.Attachment;
import com.jaeholee.devhub.domain.AttachmentType;
import com.jaeholee.devhub.domain.Post;
import com.jaeholee.devhub.mybatis.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class PostService {

    private final PostMapper postMapper;

    String imagePath = "app/data/image";
    String videoPath = "app/data/video";

    public List<Post> selectAllPosts(){
        return postMapper.selectAllPosts();
    }

    public List<Attachment> selectAttachmentsByPostId(long id){
        return postMapper.selectAttachmentsByPostId(id);
    }

    public void initializeDB(){
        File imageFolder = new File(imagePath);
        File[] imageFiles = imageFolder.listFiles();
        if(imageFiles != null) {
            for (File imageFile : imageFiles) {

                String fileName = imageFile.getName();

                String fileExtension = fileName.substring(fileName.lastIndexOf("."));

                String thumbnailName = fileName.replace(fileExtension, "_thumbnail" + fileExtension);

                Post post = Post.builder()
                        .title(imageFile.getName())
                        .build();
                postMapper.insertPost(post);

                Attachment attachment = Attachment.builder()
                        .title(imageFile.getName())
                        .attachmentType(AttachmentType.IMAGE)
                        .path("/image/" + imageFile.getName())
                        .postId(post.getId())
                        .thumbnail("/imageThumbnail/" + thumbnailName)
                        .build();
                postMapper.insertAttachment(attachment);
            }
        }

        File videoFolder = new File(videoPath);
        File[] videoFiles = videoFolder.listFiles();
        if(videoFiles != null) {
            for (File videoFile : videoFiles) {

                String fileName = videoFile.getName();

                String fileExtension = fileName.substring(fileName.lastIndexOf("."));

                String thumbnailName = fileName.replace(fileExtension, "_thumbnail.jpg");

                Post post = Post.builder()
                        .title(videoFile.getName())
                        .build();
                postMapper.insertPost(post);

                Attachment attachment = Attachment.builder()
                        .title(videoFile.getName())
                        .attachmentType(AttachmentType.VIDEO)
                        .path("video/" + videoFile.getName())
                        .postId(post.getId())
                        .thumbnail("videoThumbnail/" + thumbnailName)
                        .build();
                postMapper.insertAttachment(attachment);
            }
        }
    }
}
