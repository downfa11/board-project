package com.example.boardproject.Service;

import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.UserDTO;
import com.example.boardproject.Mapper.PostMapper;
import com.example.boardproject.Mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PostServiceImplement implements PostService{

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserProfileMapper userProfileMapper;


    @Override
    public void register(String Id, PostDTO postDTO) {
        UserDTO memberinfo = userProfileMapper.getUserProfile(Id);
        postDTO.setUserId(memberinfo.getId());
        postDTO.setCreateAt(new Date());

        if(memberinfo!=null){
            postMapper.register(postDTO);
        } else{
            log.error("register error: {}",postDTO);
            throw new RuntimeException("register error: 게시글 등록 메소드를 확인해주세요."+postDTO);
        }
    }

    @Override
    public List<PostDTO> getMyPost(int accountId) {
        List<PostDTO> postDTOList = postMapper.selectMyPost(accountId);
        return postDTOList;
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        if(postDTO!=null && postDTO.getUserId()!=0){
            postMapper.updatePost(postDTO);
        }  else{
            log.error("update error: {}",postDTO);
            throw new RuntimeException("update error: 게시글 수정 메소드를 확인해주세요."+postDTO);
        }
    }

    @Override
    public void deletePost(int userId, int postId) {
        if(userId!=0 && postId!=0){
            postMapper.deletePost(postId);
        }  else{
            log.error("delete error: {}",postId);
            throw new RuntimeException("delete error: 게시글 삭제 메소드를 확인해주세요."+postId);
        }
    }
}
