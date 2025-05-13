package com.jaeholee.devhub.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class Post {
    private int id;

    @NotNull
    private String title;

    @NotNull
    private String path;

    @NotNull
    private String thumbnail_path;

    @NotNull
    private String content_type;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private int heart;

    @NotNull
    private String creator;

    private List<Reply> replies;
}
