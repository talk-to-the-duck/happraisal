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
