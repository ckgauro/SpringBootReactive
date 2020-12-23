# Section 19: Streaming Real Time Data using WebFlux -Server Side Events (SSE)  ---Not Started 

#### Lect 81 What is a Streaming EndPoint
```
    What is a Streaming EndPoint
    Server-Sent Events
    Use Cases

```
--------
#### Lect 82 Setting Up the Capped Collection -MongoDB
```
    Tailable Cursor
        https://docs.mongodb.com/manual/core/tailable-cursors/
    Capped Collections
        https://docs.mongodb.com/manual/core/capped-collections/
        
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/document/ItemCapped.java
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/initialize/ItemDataInitializer.java
     private void createCappedCollection() {
[Run the application]

```
--------
#### Lect 83 Build the Reactive ItemCapped Mongo Repository
```

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/repository/ItemReactiveCappedRepository.java

```
--------
#### Lect 84 Initialize the data using commandLine Runner

```

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/initialize/ItemDataInitializer.java
       public void dataSetUpforCappedCollection(){
        public void run(String... args) throws Exception {
       
[Run the application]    
    
```
--------
#### Lect 85 Build the Stream Endpoint using COntroller

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/constants/ItemConstants.java

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemStreamController.java

[Run the application]  

localhost:8080/v1/stream/iteams



    
```
--------
#### Lect 86 JUNIT for Stream Endpoint using COntroller

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemStreamControllerTest.java

[Run the Test]  


    
```
--------
#### Lect 87 Bbuild the Stream EndPoint using Functional Web

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/router/ItemsRouter.java
       public RouterFunction<ServerResponse> itemStreamRoute(ItemsHandler itemsHandler){
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
      public Mono<ServerResponse> itemsStream(ServerRequest serverRequest){
[Run the Test]  
localhost:8080/v1/fun/stream/items

    
```
--------
#### Lect 88 Bbuild the Stream EndPoint using Functional Web

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemStreamsHandlerTest.java




