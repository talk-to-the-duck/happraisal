package dev.t3d.happraisal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.Interview;
import dev.t3d.happraisal.entity.Person;
import dev.t3d.happraisal.entity.QuestionAnswer;
import dev.t3d.happraisal.service.InterviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(controllers = InterviewController.class)
public class InterviewControllerITest {

  @Autowired Jackson2ObjectMapperBuilder mapperBuilder;

  @Autowired private MockMvc mockMvc;

  @MockBean private InterviewService interviewService;

  @Test
  @DisplayName("Should get all interviews")
  void should_get_persons() throws Exception {
    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/interviews")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(interviewService).should().findAll();
  }

  @Test
  @DisplayName("Should get an interview by its id")
  void should_get_form_by_id() throws Exception {
    // given
    var interviewId = UUID.fromString("27fabe0d-fe8e-4ae9-8cf5-71c13df57d4d");

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/interviews/{interviewId}", interviewId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(interviewService).should().getById(interviewId);
  }

  @Test
  @DisplayName("Should create an interview")
  void should_create_interview() throws Exception {
    // given
    var interviewToSave = getInterview();
    var interviewSaved = getInterview();
    interviewSaved.setId(UUID.fromString("b9bd9655-d423-4900-8b12-8dc83d3ff94d"));

    BDDMockito.given(interviewService.create(any())).willReturn(interviewSaved);

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/interviews")
                .content(toJson(interviewToSave))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    // then
    BDDMockito.then(interviewService).should().create(eq(interviewToSave));
  }

  private String toJson(Object object) throws JsonProcessingException {
    return mapperBuilder.build().writeValueAsString(object);
  }

  private Interview getInterview() {
    return new Interview(
        null,
        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC),
        new Person(
            UUID.fromString("e04ac226-ac21-46af-8fd6-c5e808e4c762"), "lastName", "firstName", true),
        new Person(
            UUID.fromString("e04ac226-ac21-46af-8fd6-c5e808e4c762"),
            "lastName",
            "firstName",
            false),
        new Form(
            UUID.fromString("43793644-4323-407a-9418-279500b42f56"),
            Set.of(
                new QuestionAnswer(
                    UUID.fromString("b78cea0e-8622-42da-86c1-c0f32721c7b1"),
                    "question",
                    "answer",
                    null))));
  }
}
