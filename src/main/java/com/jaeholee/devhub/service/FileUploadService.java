package com.jaeholee.devhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class FileUploadService {

    private final ThumbnailService thumbnailService;

    private final String path = "C:\\Users\\KDT-25\\Desktop\\gallery\\";

    public String[] saveFile(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        String uniqueFileName = "";
        String[] result = new String[2];

        String contentType = file.getContentType();
        if (contentType != null){
            return null;
        }
        extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (contentType.startsWith("image/")){
            String uid = UUID.randomUUID().toString();
            uniqueFileName = uid + extension;
            String image_folder = path + "image";
            File destinationFile = new File(image_folder + uniqueFileName);
            file.transferTo(destinationFile);
            String imageThumb_folder = path + "imageThumbnail";
            thumbnailService.createImageThumbnail(image_folder + uniqueFileName, imageThumb_folder + uid + "_thumbnail" + extension);
            result[0] = image_folder + uniqueFileName;
            result[1] = imageThumb_folder + uid + "_thumbnail" + extension;
        } else if (contentType.startsWith("video/")){
            String uid = UUID.randomUUID().toString();
            uniqueFileName = uid + extension;
            String video_folder = path + "video";
            File destinationFile = new File(video_folder + uniqueFileName);
            file.transferTo(destinationFile);
            String videoThumb_folder = path + "videoThumbnail";
            thumbnailService.createVideoThumbnail(video_folder + uniqueFileName, videoThumb_folder + uid + "_thumbnail" + extension);
            result[0] = video_folder + uniqueFileName;
            result[1] = videoThumb_folder + uid + "_thumbnail" + extension;
        }
        return result;
    }
}
