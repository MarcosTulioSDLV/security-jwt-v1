package com.springboot.security_jwt_v1.services;

import com.springboot.security_jwt_v1.dtos.RoleRequestDto;
import com.springboot.security_jwt_v1.dtos.RoleResponseDto;
import com.springboot.security_jwt_v1.models.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    Page<RoleResponseDto> getAllRoles(Pageable pageable);

    RoleResponseDto getRoleById(Long id);

    List<Role> findRolesByIds(List<Long> ids);

    /*@Transactional
    RoleResponseDto addRole(RoleRequestDto roleRequestDto);*/

}
