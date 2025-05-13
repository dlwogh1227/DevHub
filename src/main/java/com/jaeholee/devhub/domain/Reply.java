package com.jaeholee.devhub.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Reply {

    private int post_id;

    @NotNull
    private String body;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @NotNull
    private String creator;
}
