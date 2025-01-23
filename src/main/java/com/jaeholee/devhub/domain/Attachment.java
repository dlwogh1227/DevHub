package com.jaeholee.devhub.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attachment {

    private Long id;
    private String title;
    private String path;
    private AttachmentType attachmentType;
    private String thumbnail;
    private Long postId;
}
