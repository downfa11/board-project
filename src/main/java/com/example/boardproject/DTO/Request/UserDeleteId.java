package com.example.boardproject.DTO.Request;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UserDeleteId {
    @NonNull
    private String id;
    @NonNull
    private String password;
}