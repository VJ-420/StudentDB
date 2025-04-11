package com.student.studentdb.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value= ResourceNotFound.class)
    public ResponseEntity ResourceNotFound(){
        logger.error("Resource not found");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= UnableToAdd.class)
    public ResponseEntity UnableToAdd(){
        logger.error("Unable to add");
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity Exception(){
        logger.error("Error Occured");
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
