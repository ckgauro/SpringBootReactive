package com.learnreactspring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;

/**
 * @author Chandra
 */
public class EventLoopTest {
    @Test
    public void noOfProcessors(){
        System.out.println("============Starts===========");
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("============Ends===========");
    }
}
