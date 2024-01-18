package com.example.boardproject.Service;

import com.example.boardproject.DTO.UserDTO;
import com.example.boardproject.Exception.DuplicateIdException;
import com.example.boardproject.Mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.boardproject.Utils.SHA256util.encryptSHA256;

@Service
@Log4j2
public class UserServiceImplement implements UserService{

    @Autowired
    private UserProfileMapper userProfileMapper;

    public UserServiceImplement(UserProfileMapper userProfileMapper){
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public void register(UserDTO userProfile) {
        boolean dupIdResult = isDuplicateId(userProfile.getUserId());
         if(dupIdResult)
             throw new DuplicateIdException("중복된 아이디입니다.");

         userProfile.setCreateAt(new Date());
         userProfile.setPassword(encryptSHA256(userProfile.getPassword()));

         int insertCount = userProfileMapper.register(userProfile);
         if(insertCount!=1){
             log.error("insertMember error : {}",userProfile);
             throw new RuntimeException(
                     "insertUser error : 회원가입 method를 확인하세요\n"+"Params: "+userProfile
             );
         }
    }

    @Override
    public UserDTO login(String id, String password) {
        String cryptoPassword = encryptSHA256(password);
        UserDTO memberinfo = userProfileMapper.findByIdAndPassword(id,cryptoPassword);
        return memberinfo;
    }

    @Override
    public boolean isDuplicateId(String id) {
        return false;
    }

    @Override
    public UserDTO getUserInfo(String userid) {
        return null;
    }

    @Override
    public void updatePassword(String id, String beforePassword, String afterPassword) {
        String cryptoPassword = encryptSHA256(beforePassword);
        UserDTO memberinfo = userProfileMapper.findByIdAndPassword(id,cryptoPassword);

        if(memberinfo !=null){
            memberinfo.setPassword(encryptSHA256(afterPassword));
            int insertCount = userProfileMapper.updatePassword(memberinfo);
        }else {
            log.error("updatePassword error: {}",memberinfo);
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public void deleteId(String id, String password) {
        String cryptoPassword = encryptSHA256(password);
        UserDTO memberinfo = userProfileMapper.findByIdAndPassword(id,password);
        if(memberinfo!=null){
            int deleteCount = userProfileMapper.deleteUserProfile(id);
        } else {
            log.error("deleteId error: {}",memberinfo);
            throw new RuntimeException("회원정보가 일치하지 않습니다.");
        }
    }
}
