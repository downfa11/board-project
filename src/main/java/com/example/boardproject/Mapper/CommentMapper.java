package com.example.boardproject.Mapper;
import com.example.boardproject.DTO.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public int register(CommentDTO commentDTO);
    public List<CommentDTO> selectMyComment(int accountId);
    public void updateComment(CommentDTO commentDTO);
    public int deleteComment(int commentId);
}
