package com.springboot.security_jwt_v1.dtos;

import com.springboot.security_jwt_v1.enums.RoleName;
import com.springboot.security_jwt_v1.models.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RoleResponseDto {

    private Long id;

    private RoleName roleName;


}
