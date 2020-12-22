# Section 14: Reactive programming API Using Function and Router  ---Not Started 

#### Lect 60 & 61
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/constants/ItemConstants.java

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/router/ItemsRouter.java
    public RouterFunction<ServerResponse> itemsRoute(ItemsHandler itemsHandler){

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
      public Mono<ServerResponse> getAllItems(ServerRequest serverRequest) {
      
[Run application]
localhost:8080/v1/fun/items

```

#### Lect 62
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemHandlerTest.java
      public void getAllItems(){
      
java.lamg.assertionError: Response header <Click to see difference>      
[Run Test]


```

#### Assignment
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemHandlerTest.java
      public void getAllItems(){
      
java.lamg.assertionError: Response header <Click to see difference>      
[Run Test]
