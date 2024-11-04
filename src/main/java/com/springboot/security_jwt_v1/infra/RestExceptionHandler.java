package com.springboot.security_jwt_v1.infra;

import com.springboot.security_jwt_v1.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    //For Role class
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException e){
        return handleCustomerException(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNameExistsException.class)
    public ResponseEntity<Object> handleRoleNameExistsException(RoleNameExistsException e){
        return handleCustomerException(e,HttpStatus.BAD_REQUEST);
    }

    //Note: This exception is thrown from UserServiceImp Class
    @ExceptionHandler(InvalidRoleIdsException.class)
    public ResponseEntity<Object> handleInvalidRoleIdsException(InvalidRoleIdsException e){
        return handleCustomerException(e,HttpStatus.BAD_REQUEST);
    }

    //-----
    //For User class

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        return handleCustomerException(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(UsernameExistsException e){
        return handleCustomerException(e,HttpStatus.BAD_REQUEST);
    }
    //-----
    //For Product class

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e){
        return handleCustomerException(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductCodeExistsException.class)
    public ResponseEntity<Object> handleProductCodeExistsException(ProductCodeExistsException e){
        return handleCustomerException(e,HttpStatus.BAD_REQUEST);
    }
    //-----

    private ResponseEntity<Object> handleCustomerException(RuntimeException e,HttpStatus httpStatus){
        Map<String,Object> responseMessage= getResponseMessage(e.getMessage(),httpStatus);
        return new ResponseEntity<>(responseMessage,httpStatus);
    }

    private Map<String,Object> getResponseMessage(String message,HttpStatus httpStatus){
        Map<String,Object> responseMessage= new LinkedHashMap<>();
        responseMessage.put("message",message);
        responseMessage.put("status",httpStatus.value());
        return responseMessage;
    }

}
