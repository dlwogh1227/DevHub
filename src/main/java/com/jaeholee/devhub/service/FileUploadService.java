package com.jaeholee.devhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class FileUploadService {
    public String saveFile(MultipartFile file, String path) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));
            String uid = UUID.randomUUID().toString();
            String uniqueFileName = uid + extension;

            File destinationFile = new File(path + uniqueFileName);
            file.transferTo(destinationFile);
            return uniqueFileName;
        } catch (Exception e) {
            log.error("파일 업로드 에러{}", String.valueOf(e));
            return null;
        }
    }

    public void deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.delete()){
            throw new IOException("파일 삭제 중 오류 발생");
        }
    }
}
