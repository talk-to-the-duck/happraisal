/* (C)2023-2024 */
package dev.t3d.happraisal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.Interview;
import dev.t3d.happraisal.entity.Person;
import io.restassured.RestAssured;
import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("system-test")
class PostInterviewSTest extends AbstractIntegrationTestWithDatabase {
  @Autowired Jackson2ObjectMapperBuilder mapperBuilder;

  @LocalServerPort private Integer port;

  @BeforeAll
  public static void setup() {
    RestAssured.baseURI = "http://localhost";
  }

  @Test
  @Sql(scripts = "/sql_scripts/init_database.sql")
  void should_create_interview() throws JsonProcessingException {
    var manager =
        new Person(UUID.fromString("8a37adbc-0c29-491a-8b6f-f10a4219fb91"), null, null, true);
    var employee =
        new Person(UUID.fromString("3556c835-3bcd-420e-bb6e-a8b7c0bb139d"), null, null, false);
    var form = new Form(UUID.fromString("df9b2aad-d32e-469e-b434-be71a5531a35"), new HashSet<>());
    var interview = new Interview(null, Instant.now(), manager, employee, form);

    // @formatter:off
    RestAssured.given()
        .header("Content-type", "application/json")
        .and()
        .port(port)
        .body(mapperBuilder.build().writeValueAsString(interview))
        .when()
        .post("/interviews")
        .then()
        .statusCode(HttpStatus.SC_CREATED);
    // @formatter:on
  }
}
