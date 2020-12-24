# Section 14: Reactive programming API Using Function and Router  --- Ongoing

#### Lect 60 & 61  --Ongoing
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
     
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
      public void getAllItems(){
java.lamg.assertionError: Response header <Click to see difference>      
[Run Test]


```

#### Assignment 1
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
    public void getAllItems_approach2(){
    
     public void getAllItems_approach3(){
      
java.lamg.assertionError: Response header <Click to see difference>      
[Run Test]

```
-----------

#### Lect 63
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/router/ItemsRouter.java
     public RouterFunction<ServerResponse> itemsRoute(ItemsHandler itemsHandler){
     
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
     public Mono<ServerResponse> getOneItem(ServerRequest serverRequest) {

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemHandlerTest.java
        public void getOneItem(){
        public void getAllItems(){
        public void getOneItem_notFound(){
[Run Test]

```
-----------

#### Lect 64
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
  public Mono<ServerResponse> createItem(ServerRequest serverRequest) {
  
```

#### Assignment 2
```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemHandlerTest.java
    @Test
public void testCreateItem() {

    Item item = new Item(null,"Iphone X", 999.99);

    webTestClient.post().uri(ITEM_FUNCTIONAL_END_POINT_V1)
            .body(Mono.just(item), Item.class)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.description").isEqualTo("Iphone X")
            .jsonPath("$.price").isEqualTo("999.99");

}
 
 ```
 ------------
#### Lect 65
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
      public Mono<ServerResponse> deleteItem(ServerRequest serverRequest) {
  
```  
---------


#### Assignment 3
```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemHandlerTest.java

 @Test
    public void testDeleteOneItem() {

        webTestClient.delete().uri(ITEM_FUNCTIONAL_END_POINT_V1.concat("/{id}"), "ABC")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);

    }
    
  ```
 ------------
#### Lect 66
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
    public Mono<ServerResponse> updateItem(ServerRequest serverRequest) {
      
    
    https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/handler/ItemsHandler.java
  
```  
---------


#### Assignment 4
```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/handler/ItemHandlerTest.java

@Test
public void testUpdateItem() {
    double newPrice=129.99;
    Item item = new Item(null,"Beats HeadPhones", newPrice);
    webTestClient.put().uri(ITEM_FUNCTIONAL_END_POINT_V1.concat("/{id}"), "ABC")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .body(Mono.just(item), Item.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.price",newPrice);

}

@Test
public void testUpdateItem_notFound() {
    double newPrice=129.99;
    Item item = new Item(null,"Beats HeadPhones", newPrice);
    webTestClient.put().uri(ITEM_FUNCTIONAL_END_POINT_V1.concat("/{id}"), "DEF") //no record with this ids
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(Mono.just(item), Item.class)
            .exchange()
            .expectStatus().isNotFound();
}