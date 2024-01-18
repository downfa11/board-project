package com.example.boardproject.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int id;
    private int postId;
    private String content;
    private int commentId;
}
