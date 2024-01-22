package com.example.boardproject.Mapper;
import com.example.boardproject.DTO.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public int register(PostDTO postDTO);
    public List<PostDTO> selectMyPost(int accountId);
    public void updatePost(PostDTO postDTO);
    public int deletePost(int postId);
}
