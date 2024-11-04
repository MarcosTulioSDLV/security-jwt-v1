package com.springboot.security_jwt_v1.mappers;

import com.springboot.security_jwt_v1.dtos.LoginUserRequestDto;
import com.springboot.security_jwt_v1.dtos.UserRequestDto;
import com.springboot.security_jwt_v1.dtos.UserResponseDto;
import com.springboot.security_jwt_v1.models.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponseDto toUserResponseDto(UserEntity user){
        return modelMapper.map(user,UserResponseDto.class);
    }

    public UserEntity toUser(UserRequestDto userRequestDto){
        return modelMapper.map(userRequestDto, UserEntity.class);
    }

    public UserEntity toUser(LoginUserRequestDto loginUserRequestDto){
        return modelMapper.map(loginUserRequestDto, UserEntity.class);
    }

}
