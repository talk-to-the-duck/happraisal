/* (C)2024 */
package dev.t3d.happraisal;

import java.io.File;
import java.net.URISyntaxException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractIntegrationTestWithDatabase {

  public static final String DOCKER_COMPOSE_POSTGRESQL_YML = "docker-compose-postgresql.yml";
  static DockerComposeContainer<?> dockerComposeContainer = instantiateDockerComposeContainer();

  private static DockerComposeContainer<?> instantiateDockerComposeContainer() {
    var dockerComposeFileResource =
        AbstractIntegrationTestWithDatabase.class.getResource("/" + DOCKER_COMPOSE_POSTGRESQL_YML);
    File dockerComposeFile;
    try {
      if (dockerComposeFileResource == null) {
        throw new RuntimeException(
            String.format(
                "The docker compose file '%s' not exists", DOCKER_COMPOSE_POSTGRESQL_YML));
      }
      dockerComposeFile = new File(dockerComposeFileResource.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    var container =
        new DockerComposeContainer<>(dockerComposeFile)
            .withServices("db", "liquibase")
            .withExposedService("db", 5432);

    container.start();
    return container;
  }

  @DynamicPropertySource
  public static void registerPosgresUrl(DynamicPropertyRegistry dynamicPropertyRegistry) {
    var postgresExposedPort = dockerComposeContainer.getServicePort("db", 5432);
    dynamicPropertyRegistry.add(
        "spring.datasource.url",
        () ->
            String.format("jdbc:postgresql://localhost:%s/happraisal_dbname", postgresExposedPort));
  }
}
