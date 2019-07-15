package com.techprimes.test.testcontrollerexample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.Matches;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelloResourceTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private HelloResource helloResource;
	
	@Before
	public void setUp() throws Exception {
		
		mockMvc=MockMvcBuilders.standaloneSetup(helloResource).build();
	} 
	
	@Test
	public void testHelloWorld() throws Exception 
	{
		/*
		 * mockMvc.perform(MockMvcRequestBuilders.get("/hello")).
		 * andExpect(MockMvcResultMatchers.status().isOk()).
		 * andExpect(MockMvcResultMatchers.content().string("Hello World!"));
		 */
		mockMvc.perform(get("/hello")).
		andExpect(status().isOk()).
		andExpect(content().string("hello World!"));
		
		
	}
	@Test
	public void testHelloWorldJson() throws Exception 
	{
		
		mockMvc.perform(get("/hello/json"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title",Matchers.is("Greetings")))
		.andExpect(jsonPath("$.value", Matchers.is("Hello world")))
		.andExpect(jsonPath("$.*",Matchers.hasSize(2)));
		
		
	}
	

}
