package com.example.boardproject.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.function.BinaryOperator;

@Getter
@Setter
@ToString
public class UserDTO {

    public enum Status{
        DEFAULT,ADMIN,DELETED
    }
    private int id;
    private String userId;
    private String password;
    private String nickName;
    private boolean isAdmin;
    private Date createAt;
    private boolean isWithDraw;
    private Status status;
    private Date updateAt;
}
