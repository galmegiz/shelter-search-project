package com.sun.domain.exception;

public class RedisOperationException extends DomainException {
    String operation;
    public RedisOperationException(String operation) {
        this.operation = operation;
    }
}
