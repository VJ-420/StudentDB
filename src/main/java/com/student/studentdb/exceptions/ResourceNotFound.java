package com.student.studentdb.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String msg){
        super(msg);
    }
}
