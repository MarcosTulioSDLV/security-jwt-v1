package com.springboot.security_jwt_v1.exceptions;

public class InvalidRoleIdsException extends RuntimeException{

    public InvalidRoleIdsException(){
    }

    public InvalidRoleIdsException(String message){
        super(message);
    }

}
