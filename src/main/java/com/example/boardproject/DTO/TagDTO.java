package com.example.boardproject.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    private int id;
    private String name;
    private String url;
    private int postId;
}
