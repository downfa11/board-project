package com.example.boardproject.Controller;

import com.example.boardproject.AOP.LoginCheck;
import com.example.boardproject.DTO.CommentDTO;
import com.example.boardproject.DTO.PostDTO;
import com.example.boardproject.DTO.Response.CommonResponse;
import com.example.boardproject.DTO.TagDTO;
import com.example.boardproject.DTO.UserDTO;
import com.example.boardproject.Service.PostServiceImplement;
import com.example.boardproject.Service.UserServiceImplement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/post")
@Log4j2
public class PostController {

    private final PostServiceImplement postService;
    private final UserServiceImplement userService;

    public PostController(UserServiceImplement userService, PostServiceImplement postService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> registerPost(String accountId, @RequestBody PostDTO postDTO) {
        postService.register(accountId, postDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPost", postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("my-post")
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> myPostInfo(String accountId){
        UserDTO memberinfo = userService.getUserInfo(accountId);
        List<PostDTO> postDTOList = postService.getMyPost(memberinfo.getId());
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "myPostInfo", postDTOList);
        return ResponseEntity.ok(commonResponse);
    }

    @PatchMapping("{postId}")
    @LoginCheck(type=LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<PostResponse>> updatePost(String accountId, @PathVariable(name="postId") int postId,
                                                                   @RequestBody PostRequest postRequest){
        UserDTO userDTO = userService.getUserInfo(accountId);
        PostDTO postDTO = PostDTO.builder()
                .id(postId)
                .name(postRequest.getName())
                .content(postRequest.getContent())
                .views(postRequest.getViews())
                .categoryId(postRequest.getCategoryId())
                .userId(postRequest.getUserId())
                .fileId(postRequest.getFileId())
                .updateAt(new Date())
                .build();
        postService.updatePost(postDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "updatePost", postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @DeleteMapping({"postId"})
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<PostDeleteRequest>> deletePost(String accountId,
                                                                        @PathVariable(name="postId") int postId,
                                                                        @RequestBody PostDeleteRequest postDeleteRequest){
        UserDTO memberinfo = userService.getUserInfo(accountId);
        postService.deletePost(memberinfo.getId(), postId);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "deletePost", postDeleteRequest);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("comments")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<CommentDTO>> registerPostComment(String accountId,
                                                                          @RequestBody CommentDTO commentDTO){
        postService.registerComment(commentDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPostComment", commentDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @PatchMapping("comments/{commentId}")
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<CommentDTO>> updatePostComment(String accountId,
                                                                          @PathVariable(name="commentId") int commentId,
                                                                          @RequestBody CommentDTO commentDTO){
        UserDTO memberinfo = userService.getUserInfo(accountId);
        if(memberinfo!=null)
            postService.updateComment(commentDTO);
        postService.registerComment(commentDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "updatePostComment", commentDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @DeleteMapping("comments/{commentId}")
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<CommentDTO>> deletePostComment(String accountId,
                                                                        @PathVariable(name="commentId") int commentId){
        UserDTO memberinfo = userService.getUserInfo(accountId);
        if(memberinfo!=null)
            postService.deleteComment(memberinfo.getId(),commentId);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "deletePostComment", commentId);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("tags")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<TagDTO>> registerPostTag(String accountId,
                                                                          @RequestBody TagDTO tagDTO){
        postService.registerTag(tagDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPostTag", tagDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @PatchMapping("tags/{tagId}")
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<TagDTO>> updatePostTag(String accountId,
                                                                        @PathVariable(name="tagId") int tagId,
                                                                        @RequestBody TagDTO tagDTO){
        UserDTO memberinfo = userService.getUserInfo(accountId);
        if(memberinfo!=null)
            postService.updateTag(tagDTO);
        postService.registerTag(tagDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "updatePostTag", tagDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @DeleteMapping("tags/{tagId}")
    @LoginCheck(type= LoginCheck.userType.USER)
    public ResponseEntity<CommonResponse<TagDTO>> deletePostTag(String accountId,
                                                                @PathVariable(name="tagId") int tagId){
        UserDTO memberinfo = userService.getUserInfo(accountId);
        if(memberinfo!=null)
            postService.deleteTag(memberinfo.getId(),tagId);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "deletePostTag", tagId);
        return ResponseEntity.ok(commonResponse);
    }

    @Getter
    @AllArgsConstructor
    private static class PostResponse{
        private List<PostDTO> postDTOs;
    }

    @Setter
    @Getter
    private static class PostRequest{
        private String name;
        private String content;
        private int views;
        private int categoryId;
        private int userId;
        private int fileId;
        private Date updateAt;
    }

    @Setter
    @Getter
    private static class PostDeleteRequest{
        private int id;
        private int accountId;
    }
}