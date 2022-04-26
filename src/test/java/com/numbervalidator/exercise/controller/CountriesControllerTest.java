package com.numbervalidator.exercise.controller;

import com.numbervalidator.exercise.ExerciseApplication;
import com.numbervalidator.exercise.controller.ContriesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = ExerciseApplication.class)
@AutoConfigureMockMvc
public class CountriesControllerTest {

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  WebApplicationContext context;

	  @BeforeEach
	  public void init(){
	    mockMvc = webAppContextSetup(context).build();
	  }

	  @Test
	  public void testGetListOfContriesThenSuccess() throws Exception {
	    mockMvc.perform(get("/countries/names"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(5)));
	  }

}
