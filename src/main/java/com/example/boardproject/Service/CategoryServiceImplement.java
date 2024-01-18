package com.example.boardproject.Service;

import com.example.boardproject.DTO.CategoryDTO;
import com.example.boardproject.Mapper.CategoryMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CategoryServiceImplement implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void register(String accountId, CategoryDTO categoryDTO) {
        if(accountId!=null){
            categoryMapper.register(categoryDTO);

        } else{
            log.error("register error: {}",categoryDTO);
            throw new RuntimeException("register error: 게시글 카테고리 등록 메소드를 확인해주세요."+categoryDTO);
        }
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if(categoryDTO!=null){
            categoryMapper.updateCategory(categoryDTO);
        } else{
            log.error("update error: {}",categoryDTO);
            throw new RuntimeException("update error: 게시글 카테고리 수정 메소드를 확인해주세요."+categoryDTO);
        }
    }

    @Override
    public void delete(int categoryId) {
        if(categoryId!=0){
            categoryMapper.deleteCategory(categoryId);
        } else{
            log.error("delete error: {}",categoryId);
            throw new RuntimeException("delete error: 게시글 카테고리 삭제 메소드를 확인해주세요."+categoryId);
        }
    }
}
