package com.example.boardproject.Service;

import com.example.boardproject.DTO.CommentDTO;
import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.TagDTO;

import java.util.List;

public interface PostService {
    void register(String Id, PostDTO postDTO);
    List<PostDTO> getMyPost(int accountId);
    void updatePost(PostDTO postDTO);
    void deletePost(int userId, int postId);

    void registerComment(CommentDTO commentDTO);
    List<CommentDTO> getMyComment(int accountId);
    void updateComment(CommentDTO commentDTO);
    void deleteComment(int userId,int commentId);

    void registerTag(TagDTO tagDTO);
    void updateTag(TagDTO tagDTO);
    void deleteTag(int usrId,int tagId);
}
