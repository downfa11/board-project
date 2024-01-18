package com.example.boardproject.Service;

import com.example.boardproject.AOP.LoginCheck;
import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.Request.PostSearchRequest;
import com.example.boardproject.Mapper.PostSearchMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PostSearchServiceImplement implements PostSearchService{

    @Autowired
    private PostSearchMapper postSearchMapper;
    @Override
    @Cacheable(value="getPosts",key="'getPosts'+ #postSearchRequest.getName() + #postSearchRequest.getCategoryId()")
    public List<PostDTO> getPosts(PostSearchRequest postSearchRequest) {
        List<PostDTO> postDTOList = null;
        try{
            postDTOList = postSearchMapper.selectPosts(postSearchRequest);
        }catch ( RuntimeException e){
            log.error("selectPosts error: ",e.getMessage());
        }
        return postDTOList;
    }
}
