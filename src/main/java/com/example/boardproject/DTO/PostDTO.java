package com.example.boardproject.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private int id;
    private String name;
    private int isAdmin;
    private String content;
    private Date createAt;
    private Date updateAt;
    private int views;
    private int categoryId;
    private int userId;
    private int fileId;
    private List<TagDTO> tagDTOList;
}
