# Section 16: Handling Exception in WebFlux RestController  ---Not Started 

#### Lect 73 create new Project
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemController.java
    public Flux<Item> runtimeException(){

[Run application]
localhost:8080/v1/items/runtimeException

$curl http://localhost:8080/v1/items/runtimeException

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemController.java
    uncomment following code
 /* @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        log.error("Exception caught in handleRuntimeException :  {} " , ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }*/

[Run application]
localhost:8080/v1/items/runtimeException

$curl http://localhost:8080/v1/items/runtimeException

comment following code
/* @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        log.error("Exception caught in handleRuntimeException :  {} " , ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }*/
```
------------

#### Lect 74 Handling Exceptions using @ControllerAdvice
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/exception/ControllerExceptionHandler.java

[Run application]
localhost:8080/v1/items/runtimeException

$curl http://localhost:8080/v1/items/runtimeException

```
------------

#### Lect 75 Handling Exceptions controller JUNIT
```

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
    public void runTimeException(){