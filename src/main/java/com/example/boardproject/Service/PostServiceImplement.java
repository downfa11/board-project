package com.example.boardproject.Service;

import com.example.boardproject.DTO.CommentDTO;
import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.TagDTO;
import com.example.boardproject.DTO.UserDTO;
import com.example.boardproject.Mapper.CommentMapper;
import com.example.boardproject.Mapper.PostMapper;
import com.example.boardproject.Mapper.TagMapper;
import com.example.boardproject.Mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    @CacheEvict(value="getPosts", allEntries = true)
    public void register(String Id, PostDTO postDTO) {
        UserDTO memberinfo = userProfileMapper.getUserProfile(Id);
        postDTO.setUserId(memberinfo.getId());
        postDTO.setCreateAt(new Date());

        if(memberinfo!=null){
            postMapper.register(postDTO);
            Integer postId = postDTO.getId();
            for(int i=0; i<postDTO.getTagDTOList().size(); i++){
                TagDTO tagDTO = postDTO.getTagDTOList().get(i);
                tagMapper.register(tagDTO);
                Integer tagId = tagDTO.getId();
                tagMapper.createTag(tagId,postId);
            }
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

    @Override
    public void registerComment(CommentDTO commentDTO) {
        if(commentDTO.getCommentId()!=0){
            commentMapper.register(commentDTO);
        } else{
            log.error("registerComment error: {}",commentDTO);
            throw new RuntimeException("registerComment error: 댓글 등록 메소드를 확인해주세요."+commentDTO);
        }
    }

    @Override
    public List<CommentDTO> getMyComment(int accountId) {
        List<CommentDTO> commentDTOList = commentMapper.selectMyComment(accountId);
        return commentDTOList;
    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        if(commentDTO!=null){
            commentMapper.updateComment(commentDTO);
        }  else{
            log.error("updateComment error: {}",commentDTO);
            throw new RuntimeException("updateComment error: 댓글 수정 메소드를 확인해주세요."+commentDTO);
        }
    }

    @Override
    public void deleteComment(int userId, int commentId) {
        if(userId!=0 && commentId!=0){
            commentMapper.deleteComment(commentId);
        }  else{
            log.error("deleteComment error: {}",commentId);
            throw new RuntimeException("deleteComment error: 댓글 삭제 메소드를 확인해주세요."+commentId);
        }
    }

    @Override
    public void registerTag(TagDTO tagDTO) {
        if(tagDTO!=null){
            tagMapper.register(tagDTO);
        } else{
            log.error("registerTag error: {}",tagDTO);
            throw new RuntimeException("registerTag error: 태그 등록 메소드를 확인해주세요."+tagDTO);
        }
    }

    @Override
    public void updateTag(TagDTO tagDTO) {
        if(tagDTO!=null){
            tagMapper.updateTag(tagDTO);
        }  else{
            log.error("updateTag error: {}",tagDTO);
            throw new RuntimeException("updateTag error: 태그 수정 메소드를 확인해주세요."+tagDTO);
        }
    }

    @Override
    public void deleteTag(int userId, int tagId) {
        if(userId!=0 && tagId!=0){
            tagMapper.deleteTag(tagId);
        }  else{
            log.error("deleteTag error: {}",tagId);
            throw new RuntimeException("deleteTag error: 태그 삭제 메소드를 확인해주세요."+tagId);
        }
    }
}
