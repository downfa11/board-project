package com.example.boardproject.Mapper;

import com.example.boardproject.DTO.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    public int register(CategoryDTO categoryDTO);
    public void updateCategory(CategoryDTO categoryDTO);
    public int deleteCategory(int categoryId);
}
