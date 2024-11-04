package com.springboot.security_jwt_v1.exceptions;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(){
    }

    public RoleNotFoundException(String message){
        super(message);
    }

}
