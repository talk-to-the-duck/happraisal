
# Use postgres

## create service in docker compose

https://hub.docker.com/_/postgres

>Note: you can add plugins docker


## Add spring configuration
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/happraisal
    username: happraisameAdimin
    password: happraisale_password
    driverClassName: org.postgresql.Driver
  jpa:
    open-in-view: false
    generate-ddl: true
    show-sql: true
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    properties:
        hibernate:
            format_sql: true

```