# How to develop with a code first approach.


Please note that the code first approach is recommended only for prototyping. For production applications, a contract first approach is recommended.

In this case we already have an application that exposes some APIs using a code first approach, and we want to create the Open API definition from the existing code instead of starting from scratch. This will make it easier to write the definition file and will require less time.

The steps we will follow are:
- Enable the server side to expose an OpenAPI definition and the Swagger UI for its API.
- Download an OpenAPI definition file and save it in a directory along the source code so that we can generate Java code.
- Modify some Gradle files to automate the generation of Java source code.
- TODO Execute all the automatic tests to check that the functional behavior didn't change


# Enable the server side to expose an OpenAPI definition and the Swagger UI for its API

The first thing we have to do is to create the OpenAPI definition based on the already existing API.

A new dependency is needed. I

Add this to `build.gradle`:

```
implementation 'org.springdoc:springdoc-openapi:2.4.0'
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0'
```

and add the following snippet to `application.yaml`. This has the effect of exposing the Swagger UI excluding the SpringBoot actuator endpoints:

```
springdoc:
  swagger-ui.path: /swagger-ui.html
  show-actuator: false

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

# Download an OpenAPI definition file

Now run the application

```bash
./gradlew bootrun
```

Regarding the output format, you now have 3 choices:

- **JSON:** a human-readable file that can also be read by machines.
    ```
    http://localhost:8088/v3/api-docs
    ```
    This action will allow your browser to display the Open API specification file in *JSON* format.


- **YAML:** a human-readable file that can also be read by machines.
    ```
    http://localhost:8088/v3/api-docs.yaml
    ```
    This action will download the Open API specification file in *YAML* format.


- **SwaggerUI:** a html page, most suitable for developing purposes.
    ```
    http://localhost:8088/swagger-ui/index.html
    ```

You can put the YAML OpenAPI definition in `contract/happraisal-openapi-docs.yaml`.





COMMENT: THE TEXT ABOVE COVERS THE COMMIT "feat: step#1 get an OpenAPI definition file using a code first approach"
COMMENT: THE TEXT BELOW is a low quality draft





# How to develop with a contract first approach.

Now that you have an OpenAPI definition file, you can generate code for both the server and the client side.
Many languages are supported but here we focus on the server side for a Java application.

We use the OpenAPI generator coming from OpenAPITools.
To do this, you have to add the plugin below to the `build.gradle` file:

```
id "org.openapi.generator" version "7.2.0"
```

then create a file `gradle/openapi.gradle` and add to it the task definition:

```
openApiValidate {
    inputSpec.set("$rootDir/openapi/happraisal-openapi-docs.yaml")
}
```


## How to work with an OpenAPI definition file?

The OpenAPI definition file is the starting point.

With a application, code for client side and server side can be generated.

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







# Client side

If you want to generate the client side, you can start from looking at this comparison:
See https://reflectoring.io/comparison-of-java-http-clients/


If you want to generate the server side, you can start from looking at this comparison:
See TODO


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

