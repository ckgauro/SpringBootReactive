# Section 17: Handling Exception in WebFlux-Functional Web  ---Not Started 

#### Lect 76 Default Exception Behavior -Function Web

```

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
     public Mono<ServerResponse> itemsEx(ServerRequest serverRequest){

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/router/ItemsRouter.java
     public RouterFunction<ServerResponse> errorRoute(ItemsHandler itemsHandler){
[Run Application]     
  
curl http://localhost:8080/fun/runtimeexception

```

---------------------
#### Lect 77 Default Exception Behavior -Function Web

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/exception/FunctionalErrorWebExceptionHandler.java

[Run Application]   


curl http://localhost:8080/fun/runtimeexception


```

---------------------
#### Lect 78 Default Exception Behavior JUNIT -Function Web

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemHandlerTest.java
     public void runTimeException(){

[Run test]