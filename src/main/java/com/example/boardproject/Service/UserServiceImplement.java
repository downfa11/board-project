package com.example.boardproject.Service;

import com.example.boardproject.DTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService{
    @Override
    public void register(UserDTO userProfile) {

    }

    @Override
    public UserDTO login(String id, String password) {
        return null;
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

    }

    @Override
    public void deleteId(String id, String password) {

    }
}
