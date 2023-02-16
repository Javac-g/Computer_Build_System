package com.anobel.exception;

public class NullEntityReferenceException extends RuntimeException {
    public NullEntityReferenceException(){}
    public NullEntityReferenceException(String msg){
        super(msg);
    }

}
