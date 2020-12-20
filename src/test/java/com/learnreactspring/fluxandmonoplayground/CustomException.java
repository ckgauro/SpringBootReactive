package com.learnreactspring.fluxandmonoplayground;

import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

/**
 * @author Chandra
 */


public class CustomException extends Throwable{
    private String message;
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomException(Throwable e) {
        this.message = e.getMessage();
    }
}
