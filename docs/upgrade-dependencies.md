## Foreword

This page defines commande to upgrade ours dependencies.


## Gradle file
To upgrade gradle we need :

- upgrade Springboot dependencies before.
````
    id 'org.springframework.boot' version '3.1.3'
    id 'io.spring.dependency-management' version '1.1.3'
````

- upgrade JDK to JDK 17
````
sourceCompatibility = '17'

````
- upgrade rest assured
- change the jdk using by gradle in intellij

- execute

```` shell
./gradlew wrapper --gradle-version 8.3
````

```` shell
./gradlew wrapper --gradle-version 8.3
````
