package com.springboot.security_jwt_v1.controllers;

import com.springboot.security_jwt_v1.dtos.RoleRequestDto;
import com.springboot.security_jwt_v1.dtos.RoleResponseDto;
import com.springboot.security_jwt_v1.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Page<RoleResponseDto>> getAllRoles(@PageableDefault(size = 10)Pageable pageable){
        return ResponseEntity.ok(roleService.getAllRoles(pageable));
    }

    @GetMapping(value = "/roles-by-id/{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    /*@PostMapping(value = "/roles")
    public ResponseEntity<RoleResponseDto> addRole(@RequestBody @Valid RoleRequestDto roleRequestDto){
        return new ResponseEntity<>(roleService.addRole(roleRequestDto),HttpStatus.CREATED);
    }*/

}
