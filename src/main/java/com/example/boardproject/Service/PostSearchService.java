package com.example.boardproject.Service;

import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.Request.PostSearchRequest;

import java.util.List;

public interface PostSearchService {

    List<PostDTO> getPosts(PostSearchRequest postSearchRequest);
    List<PostDTO> getPostsByTag(String tagName);
}
