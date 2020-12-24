# Section 15: Build Non Blocking Client using WebClient  ---Ongoing

#### Lect 67 create new Project
```
Create new project Client

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/tree/master/item-client
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/resources/application.properties
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/resources/curl.txt
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/java/com/learnreactivespring/domain/Item.java
   

[Run application]

```
------------

#### Lect 67 create new Project
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/java/com/learnreactivespring/controller/ItemClientController.java
    public Flux<Item> getAllItemsUsingRetrieve(){
    public Flux<Item> getAllItemsUsingExchange(){

GET ALL ITEMS
--------------
curl http://localhost:8081/client/retrieve
curl http://localhost:8081/client/exchange
    
[Run  both learnreactiveapplication and localapplication]
    
```
------------

#### Lect 68 Passing path variable using webclient
```    
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/java/com/learnreactivespring/controller/ItemClientController.java
    public Flux<Item> getAllItemsUsingExchange(){
    public Mono<Item> getOneItemUsingRetrieve(@PathVariable String id){
    
GET A SINGLE ITEM:
------------------
curl http://localhost:8081/client/retrieve/singleItem/ABC
curl http://localhost:8081/client/exchange/singleItem

    
```
------------

#### Lect 70 Post using webclient
```    
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/java/com/learnreactivespring/controller/ItemClientController.java
      public Mono<Item> createItem(@RequestBody Item item){
    public Mono<Item> getOneItemUsingRetrieve(@PathVariable String id){
POST
----
$curl -d '{"id":null,"description":"Google Nest","price":199.99}' -H "Content-Type: application/json" -X POST http://localhost:8081/client/createItem

$curl http://localhost:8081/client/retrieve


```
------------

#### Lect 71 Update using webclient
```   
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/java/com/learnreactivespring/controller/ItemClientController.java
     public Mono<Item> updateItem(@PathVariable String id,@RequestBody Item item){

PUT
----
$curl -d '{"id":null,"description":"Beats HeadPhones","price":129.99}' -H "Content-Type: application/json" -X PUT http://localhost:8081/client/updateItem/ABC
$curl http://localhost:8081/client/retrieve

```
------------

#### Lect 72 Update using webclient
```   
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/item-client/src/main/java/com/learnreactivespring/controller/ItemClientController.java
     public Mono<Void> deleteItem(@PathVariable String id){

DELETE
------
$curl -X "DELETE" http://localhost:8081/client/deleteItem/5c70479cba14f4de103e6b00
$curl http://localhost:8081/client/retrieve