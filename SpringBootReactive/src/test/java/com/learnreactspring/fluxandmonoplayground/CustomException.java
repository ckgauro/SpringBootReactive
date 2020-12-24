package com.learnreactspring.fluxandmonoplayground;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

/**
 * @author Chandra
 */

@Data

public class CustomException extends Throwable{
    private String message;
    public CustomException(Throwable e) {
        this.message = e.getMessage();
    }
//    @Override
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//

}
