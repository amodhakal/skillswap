package com.amodhakal.skillswap.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.amodhakal.skillswap.service.AuthService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthService authService;

	@Test
	void testSignup() throws Exception {
		String json = "{\"name\":\"Test\",\"email\":\"test@gmail.com\",\"password\":\"test123\"}";

		// Success
		mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());

		// Failure
		mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isBadRequest())
				.andExpect(content().string("User already exists"));
	}

	@Test
	void testSignin() throws Exception {
		authService.handleSignup("test", "test@gmail.com", "test123");
		String successJson = "{\"name\":\"Test\",\"email\":\"test@gmail.com\",\"password\":\"test123\"}";
		String failureJson = "{\"name\":\"Test\",\"email\":\"test@gmail.com\",\"password\":\"tet123\"}";

		// Success
		mockMvc.perform(post("/api/auth/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content(successJson))
				.andExpect(status().isOk());

		// Failure
		mockMvc.perform(post("/api/auth/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content(failureJson)).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid credentials"));
	}
}
