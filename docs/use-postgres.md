
# Use postgres and Liquibase

## create service in docker compose 


Objetif: execute locally database

https://hub.docker.com/_/postgres

>Note: you can add plugins docker


## Add spring configuration

Objective: connect application to database
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/happraisal
    username: happraisameAdimin
    password: happraisalePassword
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


https://docs.liquibase.com/workflows/liquibase-community/existing-project.html
## Use liquibase
Liquibase is an open-source database schema change management solution.

### generate changelog
liquibase --driver=org.postgresql.Driver --changeLogFile=happraisal.changelog.xml  --classpath=postgresql-9.4-1201-jdbc41.jar --url="jdbc:postgresql://localhost:5432/wms"  --username=<USER_NAME> --password=<PASSWD> --defaultSchemaName=<SCHEMA_NAME> generateChangeLog   


### use liquibase separately
Once the changelog has been generated, we must execute it.
In a SpringBoot project, it s possible to execute changelog at the startup but this solution should be avoided if horizontal scaling is required.

# Errors
## with docker compose

* After update username and database I got 

``` shell
psql: FATAL:  role "postgres" does not exist
```

> Soluton: remove volume
> ```` shell
> docker-compose down --volumes && docker-compose up
> ````