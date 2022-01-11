
# happraisal

Happraisal is an application that makes people working on the appraisal **h**appy.


# List of improvements

- [] Add Unit tests (Junit5, Mockito, AssertJ)
- [] Add Integration tests  (MockMvc, TestContainer)
- [] Add Component test (Rest Assured)
- [] Add SonarQube configuration
- [] Code formatting (google-java-format)
- [] Validation of code quality (spotless / checkstyle)
- [] Swagger UI
- [] Database schema migration with Liquibase
- [] Health check endpoint
- [] Hexagonal Architecture with ArchUnit
- [] Mutation tests (pitest)



# Tests
* [x] Create unit-test.gradle file
    * [x] add dependencies 

* [ ] Create integration-test.gradle file
  * [x] add sourceset https://docs.gradle.org/current/userguide/java_testing.html#sec:configuring_java_integration_tests 
  * [x] add conf
  * [x] add dependencies
  * [x] create integration test of endpoints
  * [ ] create integration test of repositories
* [ ] create component tests
  * [x] add sourceset https://docs.gradle.org/current/userguide/java_testing.html#sec:configuring_java_integration_tests
  * [x] add conf
  * [x] add dependencies
  * [ ] create integration test of endpoints
  * [ ] create integration test of repositories

## Unit Tests
To create  unit tests we use Junit5 and Mockito

## Integration Tests

All integration tests are grouped in the integration-test sourcet. 

We use 
for service's integration tests 

for endpoint's integration tests