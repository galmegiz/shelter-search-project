package com.sun.domain.exception;

import lombok.Getter;

@Getter
public class CoordinateNotFoundException extends DomainException{
    String searchQuery;

    public CoordinateNotFoundException(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
