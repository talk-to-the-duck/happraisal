# happraisal

Happraisal is an application that makes people working on the appraisal **h**appy.


# List of improvements

- [x] Add Unit tests (Junit5, Mockito, AssertJ)
- [x] Add Integration tests  (MockMvc, TestContainer)
- [x] Add Component test (Rest Assured)
- [x] Add SonarQube configuration
- [x] Code formatting: see [Spotless documentation](docs/spotless-check.md)
- [ ] Swagger UI
- [ ] Database schema migration with Liquibase
- [ ] Health check endpoint
- [ ] Hexagonal Architecture with ArchUnit
- [ ] Mutation tests (pitest)

# Tests

* [x] Create unit-test.gradle file
    * [x] add dependencies

* [x] Create integration-test.gradle file
    * [x] add sourceset https://docs.gradle.org/current/userguide/java_testing.html#sec:configuring_java_integration_tests
    * [x] add conf
    * [x] add dependencies
    * [x] create integration test of endpoints
    * [x] create integration test of repositories
* [x] create system tests
    * [x] add sourceset https://docs.gradle.org/current/userguide/java_testing.html#sec:configuring_java_integration_tests
    * [x] add conf
    * [x] add dependencies
    * [x] create system test of endpoints
    * [x] create integration test of repositories

## Unit Tests

To create unit tests we use Junit5 and Mockito

## Integration Tests

All integration tests are grouped in the integration-test source directory.

We use:
for integration tests of services, the annotations @DataJpaTest and @Sql
for integration tests of endpoints, MockMvc and the annotation @WebMvctest

## Sonarqube integration

### documentation

The documentation about the sonar gradle plugin is [here](https://docs.sonarsource.com/sonarqube/latest/analyzing-source-code/scanners/sonarscanner-for-gradle/)

### run locally

* run docker compose
* set the environment variables : 
  * SONAR_ORGANIZATION=talk-to-the-duck
  * SONAR_PROJECT_KEY=happraisal;
  * SONAR_PROJECT_NAME=happraisal;
  * SONAR_URL=http://localhost:9000

* execute

```
execute ./gradlew  sonar -Dsonar.login=<<YOUR_SONAR_LOGIN>> -Dsonar.login=<<YOUR_SONAR_PASSWORD>>
```
* access to localhost:9000/projects (local sonar url)
![sonar projects list](/docs/sonar_projects_list.png)

* click on the project to see the detail
![soar project detail](/docs/sonar_project_detail.png)
