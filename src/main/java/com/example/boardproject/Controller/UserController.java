package com.example.boardproject.Controller;

import com.example.boardproject.Service.UserService;
import com.example.boardproject.Service.UserServiceImplement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private UserServiceImplement userService;
    
    public UserController(UserServiceImplement userService){
        this.userService = userService;
    }
}
