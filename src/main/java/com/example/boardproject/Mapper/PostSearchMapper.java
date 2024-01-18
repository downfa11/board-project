package com.example.boardproject.Mapper;

import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.Request.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {
    public List<PostDTO> selectPosts(PostSearchRequest postSearchRequest);
}
