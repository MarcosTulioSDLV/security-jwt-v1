package com.springboot.security_jwt_v1.exceptions;

public class RoleNameExistsException extends RuntimeException{

    public RoleNameExistsException(){
    }

    public RoleNameExistsException(String message){
        super(message);
    }

}
