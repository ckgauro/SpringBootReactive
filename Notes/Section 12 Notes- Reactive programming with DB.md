# Section 12: Reactive programming with DB  ---Done

$ mongod --dbpath "software/mongodbData/data/db"

#### Lect 40
mongo --version

To install mongoDB
$> brew install mongodb

$brew services start mongodb

$brew services stop mongodb

Now Run the application

-----------------

#### Lect 41 Spring Profiles  --Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/resources/application.yml


[Run the application] 
Check console 

TO pass profile from terminal 
$./gradlew build -x test 

Jar is located inside build>libs folder

$java -jar -Dspring.profiles.active=prod  build/libs/learn-reactivespring-0.0.1-SNAPSHOT.jar
$java -jar -Dspring.profiles.active=dev  build/libs/learn-reactivespring-0.0.1-SNAPSHOT.jar

```

-----------------

#### Lect 42 Build the "Item" Document  -- Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/document/Item.java

```

-----------------

#### Lect 43 Build the Reactive layer "Item" Document  -- Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/repository/ItemReactiveRepository.java
```

-----------------

#### Lect 44 Build JUNIT Reactive layer "Item" Document  --Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java
    @Test
    public void getAllItems() {
        .expectNextCount(0)
        
[Run Test]

    public void setUp() {
    public void getAllItems() {
 
[Run Test]
```

-----------------

#### Lect 45 Build JUNIT Reactive layer "Item" Document ---Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java
    public void getItemByID() {
[Run Test]

 ```

-----------------

#### Lect 46 Build JUNIT Reactive layer Custom Read  ---Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/repository/ItemReactiveRepository.java

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java
   public void findItemByDescrition() {
[Run Test]

 ```

-----------------

#### Lect 47 Build JUNIT Reactive layer Save ---Done

```

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java
       public void saveItem() {
[Run Test]

 ```

-----------------

#### Lect 48 Build JUNIT Reactive layer Update ---Done

```

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java
        public void updateItem() {
[Run Test]

 ```

-----------------

#### Lect 49 Build JUNIT Reactive layer Delete ---Done

```

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java
         public void deleteItemById() {
to do: Delete direct by it
[Run Test]
        public void deleteItem() {

[Run Test]

 ```

-----------------

#### Lect 50 Build Artifact  ---Done

```

!{Revise Video}

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/build.gradle
add 
    test {
    
gradle>build double click

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java
    @DirtiesContext
    
    Add in all test

