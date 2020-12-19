# Section 12: Reactive programming with DB  ---Not Started 


#### Lect 40
mongo --version

To install mongoDB
$> brew install mongodb

$brew services start mongodb

$brew services stop mongodb

Now Run the application

-----------------

#### Lect 41 Spring Profiles

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/resources/application.yml


[Run the application] 
Check console 

TO pass profile from terminal 
$./gradlew build -x test 

Jar is located inside build>libs folder

$java -jar -Dspring.prfiles.active=prod  build/libs/learn-reactivespring-0.0.1-SNAPSHOT.jar
$java -jar -Dspring.prfiles.active=dev  build/libs/learn-reactivespring-0.0.1-SNAPSHOT.jar

```

-----------------

#### Lect 42 Build the "Item" Document



```