package com.jaeholee.devhub.domain;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class Post {
    private Long id;
    private String title;
    private List<Attachment> attachments;
    private List<Reply> reply;
    private String createdAt;
    private String updatedAt;
}
