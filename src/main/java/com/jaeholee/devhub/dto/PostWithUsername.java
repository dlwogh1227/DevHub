package com.jaeholee.devhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostWithUsername {
    private int id;

    private String title;

    private String thumbnail_path;

    private String content_type;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private int heart;

    private String username;
}
