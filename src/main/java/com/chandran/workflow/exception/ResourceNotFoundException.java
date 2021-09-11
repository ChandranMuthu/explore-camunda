package com.chandran.workflow.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException()
    {

    }
    public ResourceNotFoundException(String error)
    {
        super(error);
    }
}
