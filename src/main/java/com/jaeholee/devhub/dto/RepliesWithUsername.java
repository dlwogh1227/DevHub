package com.jaeholee.devhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepliesWithUsername {
    private int id;

    private int post_id;

    private String body;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private int user_id;

    private int up;

    private int down;

    private String username;
}
