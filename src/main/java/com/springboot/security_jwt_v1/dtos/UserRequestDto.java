package com.springboot.security_jwt_v1.dtos;

import com.springboot.security_jwt_v1.models.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull @NotEmpty
    List<Long> roleIds= new ArrayList<>();

}
