# Section 8: Bulding the Non Non blocking RESTFUL API using Annoted Controllers  ---Done
---------

### Lect 28  --Done

```
https://spring.io/
https://spring.io/reactive

```

----------------
### Lect 29  --Done

```
uncomment in build.gradle
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/FluxAndMonoController.java

 @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> returnFluxStream(){

         return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();

    }

[Run the App]
localhost:8080/flux


```

----------------
### Lect 30 Test Case  -- Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/FluxAndMonoControllerTest.java
{Revise Video code is written in different ways}


[Run test]

```

----------------
### Lect 31 Test Case  --Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/FluxAndMonoController.java
modify Code for  
    public Flux<Long> returnFluxStream(){
try in 2 different browser
[Run application]

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/FluxAndMonoControllerTest.java
      public void fluxStream(){
                        .accept(MediaType.APPLICATION_JSON_UTF8) // modify to get error

[Run test]

```

----------------
### Lect 32 Test Case --Done

```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/main/java/com/learnreactivespring/controller/FluxAndMonoController.java
    public Mono<Integer> returnMono(){
    
[Run application]

https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/controller/FluxAndMonoControllerTest.java
    public void mono(){
 [Run test]

