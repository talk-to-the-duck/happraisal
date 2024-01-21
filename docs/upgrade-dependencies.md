## Foreword

This page defines command to upgrade Happraisal dependencies.


## Gradle file
To upgrade gradle we need:

- First upgrade Springboot dependencies:
```
    id 'org.springframework.boot' version '3.1.3'
    id 'io.spring.dependency-management' version '1.1.3'
```

- Then upgrade JDK to JDK 17
```
sourceCompatibility = '17'

```
- Then upgrade rest assured.
- Then change the jdk using by gradle in IntelliJ

- execute

```shell
./gradlew wrapper --gradle-version 8.3
```

```shell
./gradlew wrapper --gradle-version 8.3
```
