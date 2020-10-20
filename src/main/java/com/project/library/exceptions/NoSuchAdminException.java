package com.project.library.exceptions;

public class NoSuchAdminException extends RuntimeException{
    public NoSuchAdminException(String message) {
        super(message);
    }
}
