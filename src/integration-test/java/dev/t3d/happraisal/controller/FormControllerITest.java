package dev.t3d.happraisal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.QuestionAnswer;
import dev.t3d.happraisal.service.FormService;
import lombok.NoArgsConstructor;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(controllers = FormController.class)
@NoArgsConstructor
public class FormControllerITest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Autowired private MockMvc mockMvc;

  @MockBean private FormService formService;

  @Test
  @DisplayName("Should get all forms")
  void should_get_form() throws Exception {
    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(formService).should().findAll();
  }

  @Test
  @DisplayName("Should get form by its id")
  void should_get_form_by_id() throws Exception {
    // given
    var formId = UUID.fromString("27fabe0d-fe8e-4ae9-8cf5-71c13df57d4d");

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/forms/{formid}", formId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(formService).should().getById(formId);
  }

  @Test
  @DisplayName("Should create a form")
  void should_create_form() throws Exception {
    // given
    var form = new Form();

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/forms")
                .content(toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    BDDMockito.then(formService).should().create(any());
  }

  @Test
  @DisplayName("Should create a form's question")
  void should_create_form_question() throws Exception {
    // given
    var formId = UUID.fromString("ace429dd-e31c-46d7-b9e2-0a11bdc830f2");
    var questionLabel = "What is your name";
    var questionAnswer = new QuestionAnswer(null, questionLabel, null, null);

    // when
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/forms/{formId}/questions", formId)
                .content(toJson(questionAnswer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    var questionAnswerArgumentCaptor = ArgumentCaptor.forClass(QuestionAnswer.class);
    BDDMockito.then(formService)
        .should()
        .createQuestion(eq(formId), questionAnswerArgumentCaptor.capture());
    var actualQuestion = questionAnswerArgumentCaptor.getValue();
    BDDAssertions.then(actualQuestion).hasFieldOrPropertyWithValue("question", questionLabel);
  }

  private String toJson(Object object) throws JsonProcessingException {
    return MAPPER.writeValueAsString(object);
  }
}
