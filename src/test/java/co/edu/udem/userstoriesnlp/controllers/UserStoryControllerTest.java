package co.edu.udem.userstoriesnlp.controllers;

import co.edu.udem.userstoriesnlp.exceptions.NotFoundException;
import co.edu.udem.userstoriesnlp.entities.UserStory;
import co.edu.udem.userstoriesnlp.repositories.UserStoryRepository;
import co.edu.udem.userstoriesnlp.service.UserStoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserStoryController.class)
@Import(UserStoryServiceImpl.class)
public class UserStoryControllerTest {
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper mapper;
  @MockBean UserStoryRepository userStoryRepository;

  UserStory RECORD_1 = new UserStory(1, "Test 1", "User story 1 description");
  UserStory RECORD_2 = new UserStory(2, "Test 2", "User story 2 description");
  UserStory RECORD_3 = new UserStory(3, "Test 3", "User story 3 description");

  @Test
  public void getAllUserStories_success() throws Exception {
    List<UserStory> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

    Mockito.when(userStoryRepository.findAll()).thenReturn(records);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/userstory").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[2].name", is("Test 3")));
  }

  @Test
  public void getUserStoryById_success() throws Exception {
    Mockito.when(userStoryRepository.findById(RECORD_1.getId()))
        .thenReturn(java.util.Optional.of(RECORD_1));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/userstory/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.name", is("Test 1")));
  }

  @Test
  public void createUserStory_success() throws Exception {
    UserStory record =
        UserStory.builder().name("New user story").description("New description").build();

    Mockito.when(userStoryRepository.save(record)).thenReturn(record);

    MockHttpServletRequestBuilder mockRequest =
        MockMvcRequestBuilders.post("/userstory")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(record));

    mockMvc
        .perform(mockRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.name", is("New user story")));
  }

  @Test
  public void updateUserStory_success() throws Exception {
    UserStory updatedRecord =
        UserStory.builder().id(1).name("Updated name").description("Updated description").build();

    Mockito.when(userStoryRepository.findById(RECORD_1.getId())).thenReturn(Optional.of(RECORD_1));
    Mockito.when(userStoryRepository.save(updatedRecord)).thenReturn(updatedRecord);

    MockHttpServletRequestBuilder mockRequest =
        MockMvcRequestBuilders.post("/userstory")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(updatedRecord));

    mockMvc
        .perform(mockRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.name", is("Updated name")));
  }

  @Test
  public void updateUserStory_nullId() throws Exception {
    UserStory updatedRecord =
        UserStory.builder().name("Updated name").description("Updated description").build();

    MockHttpServletRequestBuilder mockRequest =
        MockMvcRequestBuilders.put("/userstory/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(updatedRecord));

    mockMvc
        .perform(mockRequest)
        .andExpect(status().isNotFound());
  }

  @Test
  public void updateUserStory_recordNotFound() throws Exception {
    UserStory updatedRecord =
        UserStory.builder().id(5).name("Updated name").description("Updated description").build();

    Mockito.when(userStoryRepository.findById(updatedRecord.getId())).thenReturn(null);

    MockHttpServletRequestBuilder mockRequest =
        MockMvcRequestBuilders.put("/userstory/5")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(updatedRecord));

    mockMvc
        .perform(mockRequest)
        .andExpect(status().isNotFound())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
        .andExpect(
            result ->
                assertEquals(
                    "User story with ID 5 does not exist.",
                    result.getResolvedException().getMessage()));
  }

  @Test
  public void deleteUserStoryById_success() throws Exception {
    Mockito.when(userStoryRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/userstory/2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void deletePatientById_notFound() throws Exception {
    Mockito.when(userStoryRepository.findById(5)).thenReturn(null);

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/userstory/5").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
        .andExpect(
            result ->
                assertEquals(
                    "User story with ID 5 does not exist.",
                    result.getResolvedException().getMessage()));
  }
}
