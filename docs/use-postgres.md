
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



## Use liquibase
Liquibase is an open-source database schema change management solution.

### generate changelog
liquibase generate-changelog --changelog-file=example-changelog.yml

liquibase --driver=org.postgresql.Driver --changeLogFile=happraisal.changelog.xml  --classpath=postgresql-9.4-1201-jdbc41.jar --url="jdbc:postgresql://localhost:5432/wms"  --username=<USER_NAME> --password=<PASSWD> --defaultSchemaName=<SCHEMA_NAME> generateChangeLog   