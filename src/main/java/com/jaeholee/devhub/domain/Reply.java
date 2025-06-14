package com.jaeholee.devhub.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {

    private int id;

    private int post_id;

    @NotNull
    private String body;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private int user_id;

    private int up;

    private int down;
}
