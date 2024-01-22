package com.example.boardproject.DTO.Request;

import com.example.boardproject.DTO.CategoryDTO;
import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequest {
    private int id;
    private String name;
    private String content;
    private int views;
    private int categoryId;
    private int userId;
    private CategoryDTO.SortStatus sortStatus;
}
