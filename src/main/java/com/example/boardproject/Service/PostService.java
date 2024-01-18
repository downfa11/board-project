package com.example.boardproject.Service;

import com.example.boardproject.DTO.PostDTO;

import java.util.List;

public interface PostService {
    void register(String Id, PostDTO postDTO);
    List<PostDTO> getMyPost(int accountId);
    void updatePost(PostDTO postDTO);
    void deletePost(int userId, int postId);
}
