package com.example.boardproject.Service;

import com.example.boardproject.DTO.UserDTO;

public interface UserService {

    void register(UserDTO userProfile);
    UserDTO login(String id,String password);

    boolean isDuplicateId(String id);

    UserDTO getUserInfo(String userid);

    void updatePassword(String id, String beforePassword, String afterPassword);

    void deleteId(String id, String password);
}
