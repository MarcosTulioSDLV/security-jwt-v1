package com.springboot.security_jwt_v1.controllers;


import com.springboot.security_jwt_v1.dtos.LoginUserRequestDto;
import com.springboot.security_jwt_v1.dtos.UserRequestDto;
import com.springboot.security_jwt_v1.dtos.UserResponseDto;
import com.springboot.security_jwt_v1.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping(value = "/users-by-id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "/users-register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRequestDto userRequestDto){
        return new ResponseEntity<>(userService.registerUser(userRequestDto),HttpStatus.CREATED);
    }

    @PostMapping(value = "/users-login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginUserRequestDto loginUserRequestDto){
        String jwtToken= userService.loginUser(loginUserRequestDto);
        return new ResponseEntity<>(jwtToken,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> removeUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.ok("User removed successfully!");
    }

}

