package org.example.linkedlist;

public class CustomException extends Exception {
    public CustomException(ExceptionType e) {
        super(e.getMessage());
    }
}
