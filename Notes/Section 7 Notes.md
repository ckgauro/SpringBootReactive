# Section 7: Reactive Programming (Flux and Mono) - Hands on + JUNIT Testing  ---Not Started 
---------
Lect 13
```
in src\test create Class
    FluxAndmonoTest
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoTest.java
    fluxTest()
        test
        uncomment   /*.concatWith(Flux.error(new RuntimeException("Exception Occurred")))*/
        test
        comment   /*.concatWith(Flux.error(new RuntimeException("Exception Occurred")))*/
```
---------
Lect 14
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoTest.java
    fluxTestElements_WithoutError()
        change order
        remove any expectNotes
        comment .verifyComplete()
    
    fluxTestElements_WithError() 
    
        
    fluxTestElements_WithError1()
    
    fluxTestElementsCount_WithError()
    
```
---------
Lect 15
```
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoTest.java
    monoTest()
    monoTest_Error()
```
---------
Lect 16
```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoFactoryTest.java
    fluxUsingIterable()
    fluxUsingArray()
    fluxUsingStream()
    monoUsingJustOrEmpty()
    monoUsingSupplier()
    fluxUsingRange()
    
```
---------
Lect 17
```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoFilterTest.java
    try log() before filter 
```
---------
Lect 18, 19
```  
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoTransformTest.java
```
---------
Lect 20
``` 
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoCombineTest.java
    combineUsingMerge()
        change order in expectNext
    
```
---------
Lect 21 [revise]
``` 
https://github.com/dilipsundarraj1/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/fluxandmonoplayground/FluxAndMonoErrorTest.java


        
    
  