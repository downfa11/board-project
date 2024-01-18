package com.example.boardproject.Mapper;
import com.example.boardproject.DTO.TagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    public int register(TagDTO tagDTO);
    public void updateTag(TagDTO tagDTO);
    public void deleteTag(int tagId);
    public void createTag(Integer tagId, Integer postId);
}
