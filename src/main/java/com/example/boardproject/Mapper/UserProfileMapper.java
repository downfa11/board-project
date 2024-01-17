package com.example.boardproject.Mapper;

import com.example.boardproject.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserProfileMapper {
    public UserDTO getUserProfile(@Param("id") String usrId);
    int insertUserProfile(@Param("id") String id,@Param("password") String password,
                          @Param("name") String name, @Param("address") String address,
                          @Param("createAt") String createAt, @Param("updateAt") String updateAt);
    int deleteUserProfile(@Param("id") String id);

    public UserDTO findByIdAndPassword(@Param("id") String id, @Param("password") String password);

    int idCheck(@Param("id") String id);

    public int updatePassword(UserDTO user);
    public int updateAddress(UserDTO user);

    int register(UserDTO userDTO);
}
