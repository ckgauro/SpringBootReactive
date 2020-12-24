# Section 13: Reactive programming API EndPoint  ---Done
brew services restart mongodb

#### Lect 51  --Done
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/constants/ItemConstants.java

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemController.java
      public Flux<Item> getAllItems(){
      
[Run the application]
localhost:8080/v1/items
```
-----------

#### Lect 52 Setup Data for the application  --- Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/initialize/ItemDataInitializer.java
[Run the application]
localhost:8080/v1/items

```
-----------

#### Lect 53 Setup Junit for the application  --Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
     public void getAllItems(){
        MediaType.APPLICATION_JSON_UTF8
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/resources/application.yml
    @ActiveProfiles("test")
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/initialize/ItemDataInitializer.java
    @Profile("!test")
    
[Run test]    
```
-----------

#### Lect 54 Setup Junit for the application  --Done

```    
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
      public void getAllItems_approach2(){
       [Run test]  
    public void getAllItems_approach3(){
       [Run test]  
```
-----------

#### Lect 55 Setup Junit for the application  --Done

```    
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemController.java
    public Mono<ResponseEntity<Item>> getOneItem(@PathVariable String id){
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
     public void getOneItem(){
     public void getOneItem_notFound(){
[Run the application]
[Run test]  

```
-----------

#### Lect 56 Setup Junit for the application --Done --Done


```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemController.java
     public Mono<Item> createItem(@RequestBody Item item){
     
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
   public void createItem(){

[Run the application]
[Run test]  

```
-----------

#### Lect 57 Setup Junit for the application --Done


```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemController.java
      public Mono<Void> deleteItem(@PathVariable String id){
     
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
     public void deleteItem(){

[Run the application]
[Run test]  

```
-----------

#### Lect 58 Setup Junit for the application  --Done

```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/v1/ItemController.java
     public Mono<ResponseEntity<Item>> updateItem(@PathVariable String id,@RequestBody Item item){
      public Flux<Item> runtimeException(){
     
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/v1/ItemControllerTest.java
     public void updateItem(){
      public void updateItem_notFound(){
         public void runTimeException(){
[Run the application]
[Run test]  
[Run build gradle]







        