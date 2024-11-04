package com.springboot.security_jwt_v1.mappers;

import com.springboot.security_jwt_v1.dtos.RoleResponseDto;
import com.springboot.security_jwt_v1.models.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public RoleResponseDto toRoleResponseDto(Role role){
        return modelMapper.map(role,RoleResponseDto.class);
    }

}
