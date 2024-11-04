package com.springboot.security_jwt_v1.services;

import com.springboot.security_jwt_v1.dtos.LoginUserRequestDto;
import com.springboot.security_jwt_v1.dtos.UserRequestDto;
import com.springboot.security_jwt_v1.dtos.UserResponseDto;
import com.springboot.security_jwt_v1.exceptions.InvalidRoleIdsException;
import com.springboot.security_jwt_v1.exceptions.UserNotFoundException;
import com.springboot.security_jwt_v1.exceptions.UsernameExistsException;
import com.springboot.security_jwt_v1.mappers.UserMapper;
import com.springboot.security_jwt_v1.models.Role;
import com.springboot.security_jwt_v1.models.UserEntity;
import com.springboot.security_jwt_v1.repositories.UserRepository;
import com.springboot.security_jwt_v1.security.util.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toUserResponseDto);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserEntity user= findUserById(id);
        return userMapper.toUserResponseDto(user);
    }

    private UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id: " + id + " not found!"));
    }

    @Override
    @Transactional
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        UserEntity user= userMapper.toUser(userRequestDto);
        List<Long> roleIds= userRequestDto.getRoleIds();

        validateUniqueUsername(user);

        String encodedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        List<Role> roles= roleService.findRolesByIds(roleIds);
        validateNotEmptyList(roles);

        user.getRoles().addAll(roles);

        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    private void validateUniqueUsername(UserEntity user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UsernameExistsException("Username: "+ user.getUsername()+" already exists!");
        }
    }

    private void validateNotEmptyList(List<Role> roles) {
        if(roles.isEmpty()){
            throw new InvalidRoleIdsException("Roles do not exists!");
        }
    }

    @Override
    @Transactional
    public String loginUser(LoginUserRequestDto loginUserRequestDto) {
        UserEntity user= userMapper.toUser(loginUserRequestDto);
        var usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication= authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.createToken(authentication);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        UserEntity user= findUserById(id);
        userRepository.delete(user);
    }


}
