# How to develop with a code first approach.


Please note that the code first approach is recommended only for prototyping.

For production application, a contract first approach is recommended.

In this case we already have an application that exposes some APIs in a code first way, and we want to create the Open API definition from the existing code instead of starting from scratch.

This will make it easier to write the definition file and will require less time.


To see all the available endpoints:

Add this to `build.gradle`:

```
implementation 'org.springframework.boot:spring-boot-starter-actuator'

implementation 'org.springdoc:springdoc-openapi:2.3.0'
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0'

```

and add the following snippet to `application.yaml`

```
springdoc:
  swagger-ui.path: /swagger-ui.html
    show-actuator: true

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

Comment:
show-actuator: true --> shows the actuator endpoints (e.g. http://localhost:8088/actuator/info)


TODO COMPLETE THE LINE BELOW
Please pay attention to the fact that env and ... are exposed and this is definitely something you want to avoid in production.



run the application

```bash
./gradlew bootrun
```

Regarding the output format, you now have 2 choices:

- JSON a machine-readable file
- SwaggerUI a html page.

and head to 

```
http://localhost:8088/actuator/mappings
```

or

```
http://localhost:8088/swagger-ui/index.html
```

or

```
http://localhost:8088/v3/api-docs
```

to see the content in the browser.

If you want to download the Open API specification file in yaml format, head to this address and the file will be downloaded:

```
http://localhost:8088/v3/api-docs.yaml
```

You can find the OpenAPI definition obtained this way in `openapi/happraisal-openapi-docs.yaml`.

