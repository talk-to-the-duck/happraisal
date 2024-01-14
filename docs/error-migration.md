# Migration Gradle to  8.3

## Presentation 
this document presents the issues encountered during the migration to Gradle 8.3


## 1
````
 What went wrong:
'void org.gradle.api.internal.artifacts.dsl.LazyPublishArtifact.<init>(org.gradle.api.provider.Provider)'

````
> **solution**:  Upgrade dependencies springboot

## 2
````
 value '8.3' but:
  - Variant 'apiElements' capability org.springframework.boot:spring-boot-gradle-plugin:3.1.3 declares a library, packaged as a jar, and its dependencies declared externally:
      - Incompatible because this component declares a component for use during compile-time, compatible with Java 17 and the consumer needed a component for use during runtime, compatible with Java 11
      - Other compatible attribute:
          - Doesn't say anything about org.gradle.plugin.api-version (required '8.3')
  - Variant 'javadocElements' capability org.springframework.boot:spring-boot-gradle-plugin:3.1.3 declares a component for use during runtime, and its dependencies declared externally:
      - Incompatible because this component declares documentation and the consumer needed a library
      - Other compatible attributes:
          - Doesn't say anything about its target Java version (required compatibility with Java 11)
          - Doesn't say anything about its elements (required them packaged as a jar)
          - Doesn't say anything about org.gradle.plugin.api-version (required '8.3')
  - Variant 'mavenOptionalApiElements' capability org.springframework.boot:spring-boot-gradle-plugin-maven-optional:3.1.3 declares a library, packaged as a jar, and its dependencies declared externally:
````

> **solution**: upgrades jdk

## 3
````
happraisal:systemTest: Could not resolve org.apache.groovy:groovy:4.0.11.
Required by:
    project : > io.rest-assured:rest-assured:4.4.0 > io.rest-assured:json-path:5.3.1
    project : > io.rest-assured:rest-assured:4.4.0 > io.rest-assured:xml-path:5.3.1
    project : > io.rest-assured:rest-assured:4.4.0 > io.rest-assured:json-path:5.3.1 > io.rest-assured:rest-assured-common:5.3.1
````

## 4 
After clean install 
````

C:\Users\Emmanuel\Developpement\sources\t3d\happraisal\src\main\java\dev\t3d\happraisal\entity\Form.java:10: error: cannot find symbol
@Entity
 ^
  symbol: class Entity
````

> **solution**: replace all javax references to jakarata


## 5
clean assemble ok
After clean build
````
Caused by: java.lang.IllegalAccessError: class com.google.googlejavaformat.java.JavaInput (in unnamed module @0x4e51f79) cannot access class com.sun.tools.javac.parser.Tokens$TokenKind (in module jdk.compiler) because module jdk.compiler does not export com.sun.tools.javac.parser to unnamed module @0x4e51f79
````

> **solution**: upgradte spoless plugin to 6.20.2


## 6

````
C:\Users\Emmanuel\Developpement\sources\t3d\happraisal\src\system-test\java\dev\t3d\happraisal\controller\PostInterviewSTest.java:13: error: cannot find symbol
import org.springframework.boot.web.server.LocalServerPort;
                                          ^
  symbol:   class LocalServerPort
  location: package org.springframework.boot.web.server

````

> **solution**: replace import org.springframework.boot.web.server.LocalServerPort; to import org.springframework.boot.test.web.server.LocalServerPort;


# Upgrade others dependencies
Mockito, junit restassured