/* (C)2024 */
package dev.t3d.happraisal.controller;

import java.io.File;
import java.net.URISyntaxException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AbstractIntegrationTestWithDatabase {

  static DockerComposeContainer<?> dockerComposeContainer = instantiatetDockerComposeContainer();

  private static DockerComposeContainer<?> instantiatetDockerComposeContainer() {
    var dockerComposeFileResource =
        AbstractIntegrationTestWithDatabase.class.getResource("/docker-compose-postgresql.yml");
    File dockerComposeFile;
    try {
      assert dockerComposeFileResource != null;
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
        () -> String.format("jdbc:postgresql://localhost:%s/happraisaldb", postgresExposedPort));
  }
}
