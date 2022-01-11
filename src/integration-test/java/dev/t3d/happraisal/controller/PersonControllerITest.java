package dev.t3d.happraisal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.t3d.happraisal.entity.Person;
import dev.t3d.happraisal.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerITest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Autowired private MockMvc mockMvc;

  @MockBean private PersonService personService;

  @Test
  @DisplayName("Should get all persons")
  void should_get_persons() throws Exception {
    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(personService).should().findAll();
  }

  @Test
  @DisplayName("Should get all forms")
  void should_get_form_by_id() throws Exception {
    // given
    var personId = UUID.fromString("27fabe0d-fe8e-4ae9-8cf5-71c13df57d4d");

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/persons/{personid}", personId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(personService).should().getById(personId);
  }

  @Test
  @DisplayName("Should create a person")
  void should_create_form() throws Exception {
    // given
    var person =
        new Person(
            UUID.fromString("34d1a1f9-25f9-4aa4-98cd-01531cb8ec34"),
            "lastName",
            "firstName",
            false);

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/persons")
                .content(toJson(person))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(personService).should().create(any());
  }

  private String toJson(Object object) throws JsonProcessingException {
    return MAPPER.writeValueAsString(object);
  }
}
