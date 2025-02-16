package com.jaeholee.devhub.service;

import com.jaeholee.devhub.DevHubApplication;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.FilenameFilter;

@SpringBootTest
@Log4j2
@ContextConfiguration(classes = DevHubApplication.class)
public class ThumbnailCreatorTests {

    @Autowired
    private ThumbnailService thumbnailService;

    @Test
    public void testCreateThumbnail() {
        String imageFolderPath = "app/data/image";
        String videoFolderPath = "app/data/video";
        String imageThumbnailFolderPath = "app/data/imageThumbnail";
        String videoThumbnailFolderPath = "app/data/videoThumbnail";

        File imageFolder = new File(imageFolderPath);
        if (imageFolder.exists() && imageFolder.isDirectory()) {
            File[] imageFiles = imageFolder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
                }
            });
            if(imageFiles != null) {
                for (File imageFile : imageFiles) {
                    String baseName = imageFile.getName().substring(0, imageFile.getName().lastIndexOf('.'));
                    String extension = imageFile.getName().substring(imageFile.getName().lastIndexOf('.'));
                    String thumbnailName = baseName + "_thumbnail" + extension;

                    String thumbnailPath = imageThumbnailFolderPath + "\\" + thumbnailName;

                    thumbnailService.createImageThumbnail(imageFile.getAbsolutePath(), thumbnailPath);
                    System.out.println("이미지 썸네일 생성 완료: " + thumbnailPath);
                }
            }
        } else {
            log.error("이미지 폴더를 찾을 수 없습니다: {}", imageFolderPath);
        }

        File videoFolder = new File(videoFolderPath);
        if (videoFolder.exists() && videoFolder.isDirectory()) {
            File[] videoFiles = videoFolder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".mov");
                }
            });
            if(videoFiles != null) {
                for (File videoFile : videoFiles) {
                    String baseName = videoFile.getName().substring(0, videoFile.getName().lastIndexOf('.'));
                    String thumbnailName = baseName + "_thumbnail.jpg";  // 썸네일 파일은 .jpg 확장자로 저장

                    String thumbnailPath = videoThumbnailFolderPath + "\\" + thumbnailName;

                    thumbnailService.createVideoThumbnail(videoFile.getAbsolutePath(), thumbnailPath);
                    System.out.println("동영상 썸네일 생성 완료: " + thumbnailPath);
                }

            }
        } else {
            System.out.println("동영상 폴더를 찾을 수 없습니다: " + videoFolderPath);
        }
    }
}
