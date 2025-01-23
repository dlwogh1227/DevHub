package com.jaeholee.devhub.domain;

import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private int id;
    private String text;
}
