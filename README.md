# happraisal

Happraisal is an application that makes people working on the appraisal **h**appy.


# List of improvements

- [ ] Add Unit tests (Junit5, Mockito, AssertJ)
- [ ] Add Integration tests  (MockMvc, TestContainer)
- [ ] Add Component test (Rest Assured)
- [ ] Add SonarQube configuration
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
To create  unit tests we use Junit5 and Mockito

## Integration Tests

All integration tests are grouped in the integration-test source directory.

We use
for integration tests of services, the annotations @DataJpaTest and @Sql
for integration tests of endpoints, MockMvc and the annotation @WebMvctest
