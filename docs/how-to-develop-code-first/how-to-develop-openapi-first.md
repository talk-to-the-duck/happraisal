
# How to develop with a contract first approach.

COMMENT: THE TEXT BELOW is a low quality draft


Now that you have an OpenAPI definition file, you can generate code for both the server and the client side.

We use the OpenAPI generator coming from OpenAPITools. Many languages are supported but here we focus on the server side for a Java application. Any Open API definition file has to be valid. Before generating the code, the input file must be valid.

When invoked, the first thing that the generator does is calling a validator to check for the validity of the Open API definition file. If the file is valid, the generators create the code.

While the generator can be called from the command line, the best practice is to run it during the build process.

Let's see how to configure Gradle to invoke the generator.

First, add the OpenAPI generator plugin to the `build.gradle` file:

```
id "org.openapi.generator" version "7.2.0"
```

then create a file `gradle/openapi.gradle` and write in it the following task definition:

```
openApiValidate {
    inputSpec.set("$rootDir/contract/happraisal-openapi-docs.yaml")
}
```

You need to instruct Gradle to use the generated code:

```
sourceSets {
    main {
        java {
            srcDir file("${buildDir}/generated-openapi-springboot/src/main/java")
        }
    }
}
```

The primary purpose of the sourceSets configuration is to instruct Gradle to include the generated OpenAPI code as part of the main source set during the build process. This ensures that the generated code is compiled, packaged, and deployed along with the rest of the application, ensuring that the generated code is properly integrated into the build process.



## How to work with an OpenAPI definition file?

The OpenAPI definition file is the starting point.

Code can be generated for both client and server side.

A Gradle plugin to integrate the generator in the gradle build configuration makes the use of the application more comfortable,

The Gradle plugin tends to keep a 1 to 1 mapping with the application commands.

Here
https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/README.adoc

you can find all the tasks of the OpenAPI tool.


| task name         | 	description                                                                                |
|-------------------|---------------------------------------------------------------------------------------------|
| openApiGenerate   | Generate code via Open API Tools Generator for Open API 2.0 or 3.x specification documents. |
| openApiGenerators | Lists generators available via Open API Generators.                                         |
| openApiMeta       | Generates a new generator to be consumed via Open API Generator.                            |
| openApiValidate   | Validates an Open API 2.0 or 3.x specification document.                                    |


To list all the available generators, run this command:

```
./gradlew openApiGenerators
```

The generous output list of server generators will show, among many others, the Java possibilities below:

```

SERVER side generators for Java:

    - java-camel
    - java-helidon-server (beta)
    - java-inflector
    - java-micronaut-server (beta)
    - java-msf4j
    - java-pkmst
    - java-play-framework
    - java-undertow-server
    - java-vertx-web (beta)
    - jaxrs-cxf
    - jaxrs-cxf-cdi
    - jaxrs-cxf-extended
    - jaxrs-jersey
    - jaxrs-resteasy
    - jaxrs-resteasy-eap
    - jaxrs-spec
    - spring
```
which is a whopping set of 17 different ways to do it.

To read the documentation for all of them, go to the page

```
https://openapi-generator.tech/docs/generators
```

There are many choices but just reading this documentation doesn't tell you what is the best choice.

TODO complete this part

# Enable the Swagger User Interface


# Server side

To generate the server side code, add the snippet below to the file `gradle/openapi.gradle`:

```
openApiGenerate {

// too many lines to paste here, see the file

}
```

Now you run the command below in the terminal to generate the server side code:

```
./gradlew openApiGenerate
```

and the code will be generated in the folder `build/generated-openapi-springboot` as configured by the line

```
outputDir = project.layout.buildDirectory.dir("generated-openapi-springboot").get().asFile.path
```

# Client side

If you want to generate the client side, you can start from looking at this comparison:
See https://reflectoring.io/comparison-of-java-http-clients/


If you want to generate the server side, you can start from looking at this comparison:
See TODO

# How to generate an OpenAPI specification for any site:

See how to generate an OpenAPI spec for any site:
https://medium.com/@angela.tt/the-easiest-and-quickest-way-to-generate-an-openapi-spec-for-an-existing-website-12b5ad6e36db

OpenAPI diff
https://www.oasdiff.com/
https://github.com/OpenAPITools/openapi-diff


Some information about delegator and delegate

Delegation An implementation mechanism in which an object forwards or delegates a request to another object.
The delegate carries out the request on behalf of the original object.

A delegate is a helper object used by another object.
https://rosettacode.org/wiki/Delegates






```
./gradlew openApiValidate
```






TODO


If you wish to use a different OpenApi definition, then you have to switch to a new profile and start the SpringBoot application using that profile as described in the page:

https://stackoverflow.com/questions/70999895/how-to-point-to-application-local-yml-instead-of-application-dev-yml-in-intellij

that suggests this:
--spring.config.additional-location=classpath:application-local.yml --spring.profiles.active=local



Sources:jdbc:h2:mem:mydb
https://springdoc.org/

https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints

