package com.jaeholee.devhub.service;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class ThumbnailService {

    public void createImageThumbnail(String imagePath, String thumbnailPath) {
        try{
            File thumbnailFile = new File(thumbnailPath);
            if (!thumbnailFile.exists()){
                Thumbnails.of(imagePath)
                        .size(640, 360)
                        .toFile(thumbnailPath);
            } else {
                log.info("{} already exists", thumbnailPath);
            }
        } catch (IOException e) {
            log.error(e);
            log.error("이미지 썸네일 생성 실패");
        }
    }

    public void createVideoThumbnail(String videoPath, String thumbnailPath) {
        try {
            File thumbnailFile = new File(thumbnailPath);
            if (!thumbnailFile.exists()){
                File videoFile = new File(videoPath);
                String ffmpegCommand = String.format("ffmpeg -ss 00:00:01.000 -i %s -an -vframes 1 -s 640x360 %s", videoFile.getAbsolutePath(), thumbnailPath);

                Process process = Runtime.getRuntime().exec(ffmpegCommand);
                process.waitFor(1, TimeUnit.SECONDS);
            }
        } catch (IOException | InterruptedException e) {
            log.error(e);
            throw new RuntimeException("동영상 썸네일 생성 실패", e);
        }
    }
}
