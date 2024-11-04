package com.springboot.security_jwt_v1.security;

import com.springboot.security_jwt_v1.models.UserEntity;
import com.springboot.security_jwt_v1.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional//Note: this is necessary because roles are Lazy be default
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity= userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username: "+username+" not found!"));
        userEntity.getRoles().size();//Note: this is necessary because roles are Lazy be default
        //OR: Hibernate.initialize(userEntity.getRoles());//Force initialization of the role collection
        return userEntity;
    }

}
