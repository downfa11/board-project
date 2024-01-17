package com.example.boardproject.Controller;

import com.example.boardproject.DTO.Request.UserDeleteId;
import com.example.boardproject.DTO.Request.UserLoginRequest;
import com.example.boardproject.DTO.Request.UserUpdatePasswordRequest;
import com.example.boardproject.DTO.Response.LoginResponse;
import com.example.boardproject.DTO.Response.UserInfoResponse;
import com.example.boardproject.DTO.UserDTO;
import com.example.boardproject.Service.UserServiceImplement;
import com.example.boardproject.Utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private UserServiceImplement userService;
    private static LoginResponse loginResponse;

    public UserController(UserServiceImplement userService){
        this.userService = userService;
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserDTO userDTO){
        if(userDTO.hasNullDataBeforeRegister(userDTO)){
            throw new RuntimeException("회원가입 정보를 확인해주세요.");
        }

        userService.register(userDTO);
    }

    @PostMapping("sign-in")
    public HttpStatus Login(@RequestBody UserLoginRequest userLoginRequest,
                            HttpSession session){
        ResponseEntity<LoginResponse> responseEntity = null;
        String id = userLoginRequest.getUserId();
        String password =userLoginRequest.getPassword();
        UserDTO userinfo = userService.login(id,password);

        if(userinfo==null)
        {
        return HttpStatus.NOT_FOUND; }
        else if(userinfo!=null){
            loginResponse = LoginResponse.success(userinfo);
            if(userinfo.getStatus() == (UserDTO.Status.ADMIN))
                SessionUtil.setLoginAdminId(session,id);
            else SessionUtil.setLoginMemberId(session,id);


            responseEntity = new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);

        } else{
            throw new RuntimeException("Login error: 사용자의 정보가 없거나 지원하지 않는 사용자입니다.");
        }

        return HttpStatus.OK;
    }

    @GetMapping("my-info")
    public UserInfoResponse memberinfo(HttpSession session){
        String id = SessionUtil.getLoginMemberId(session);
        if(id == null) id = SessionUtil.getLoginAdminId(session);
        UserDTO memberinfo = userService.getUserInfo(id);
        return new UserInfoResponse(memberinfo);
    }

    @PutMapping("logout")
    public void logout(HttpSession session){
        SessionUtil.clear(session);
    }

    @PatchMapping("password") //Put은 전체수정, 일부 수정은 Patch
    public ResponseEntity<LoginResponse> updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest,
                                                            HttpSession session) {
        ResponseEntity<LoginResponse> responseEntity = null;
        String id = SessionUtil.getLoginMemberId(session);
        String beforePassword = userUpdatePasswordRequest.getBeforePassword();
        String afterPassword = userUpdatePasswordRequest.getAfterPassword();

        try {
           userService.updatePassword(id,beforePassword,afterPassword);
           ResponseEntity.ok(new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK));

        } catch (IllegalArgumentException e) {
            log.error("updatePassword error", e);
            responseEntity = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }


    @DeleteMapping
    public ResponseEntity<LoginResponse> deleteId(@RequestBody UserDeleteId userDeleteId,
                                                  HttpSession session){
        ResponseEntity<LoginResponse> responseEntity = null;
        String id = SessionUtil.getLoginMemberId(session);

        try{
            userService.deleteId(id,userDeleteId.getPassword());
        } catch (RuntimeException e){
            log.error("deleteId error");
            responseEntity = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);

        }
        return responseEntity;
    }
}
