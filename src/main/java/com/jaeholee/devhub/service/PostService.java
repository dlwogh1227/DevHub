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

    String imageAbPath = "D:\\DevHub\\src\\main\\resources\\static\\gallery\\image";
    String videoAbPath = "D:\\DevHub\\src\\main\\resources\\static\\gallery\\video";
    String imageThumbnailFolderPath = "/gallery/imageThumbnail";
    String videoThumbnailFolderPath = "/gallery/videoThumbnail";
    String imagePath = "/gallery/image";
    String videoPath = "/gallery/video";

    public List<Post> selectAllPosts(){
        return postMapper.selectAllPosts();
    }

    public void initailizeDB(){
        File imageFolder = new File(imageAbPath);
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
                        .path(imagePath + "/" + imageFile.getName())
                        .postId(post.getId())
                        .thumbnail(imageThumbnailFolderPath + "/" + thumbnailName)
                        .build();
                postMapper.insertAttachment(attachment);
            }
        }

        File videoFolder = new File(videoAbPath);
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
                        .path(videoPath + "/" + videoFile.getName())
                        .postId(post.getId())
                        .thumbnail(videoThumbnailFolderPath + "/" + thumbnailName)
                        .build();
                postMapper.insertAttachment(attachment);
            }
        }
    }
}
