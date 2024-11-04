package com.springboot.security_jwt_v1.dtos;

import com.springboot.security_jwt_v1.models.Role;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserResponseDto {

    private Long id;

    private String username;

    private List<Role> roles= new ArrayList<>();

}
