package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomePageControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void validateGreetings() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/greeting")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(view().name("greeting"))
				.andExpect(model().attributeExists("name"));
	}

	@Test
	public void testRegistration() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/register")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(view().name("register"));

		mockMvc.perform(MockMvcRequestBuilders.post("/register")
				.accept(MediaType.APPLICATION_JSON)
				.param("firstName", "Nagesh")
				.param("lastName", "Salunke")
				.param("userName", "nsalunke@"))
				.andExpect(view().name(startsWith("redirect")))
				.andExpect(view().name(endsWith("Nagesh")));
	}

}
