package com.example.boardproject.Controller;

import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.Request.PostSearchRequest;
import com.example.boardproject.Service.PostSearchServiceImplement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@Log4j2
@RequiredArgsConstructor
public class PostSearchController {
    private final PostSearchServiceImplement postSearchService;

    @PostMapping
    public PostSearchResponse search(@RequestBody PostSearchRequest postSearchRequest){
        List<PostDTO> postDTOList = postSearchService.getPosts(postSearchRequest);
        return new PostSearchResponse(postDTOList);
    }

    @GetMapping
    public PostSearchResponse searchByTagName(String tagName){
        List<PostDTO> postDTOList = postSearchService.getPostsByTag(tagName);
        return new PostSearchResponse(postDTOList);
    }


    @Getter
    @AllArgsConstructor
    private static class PostSearchResponse{
        private List<PostDTO> postDTOList;
    }
}
