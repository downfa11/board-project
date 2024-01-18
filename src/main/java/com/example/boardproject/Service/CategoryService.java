package com.example.boardproject.Service;

import com.example.boardproject.DTO.CategoryDTO;

public interface CategoryService {

    void register(String accountId, CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);

    void delete(int categoryId);
}
