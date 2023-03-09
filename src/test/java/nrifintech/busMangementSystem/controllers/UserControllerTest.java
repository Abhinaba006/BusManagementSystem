package nrifintech.busMangementSystem.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.User;

@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private User user1;
	private User user2;
	
	@BeforeEach
	void init() {
		user1 = new User();
		user1.setEmail("demo12@gmail.com");
		user1.setEmployeeId("E012");
		user1.setName("Demo user");
		user1.setPassword("demo123");
		user1.setType(0);
		
		user2 = new User();
		user2.setEmail("temp12@gmail.com");
		user2.setName("Temp user");
		user2.setPassword("temp123");
		user2.setType(1);
	}
	
	@Test
	void CreateUser() throws Exception {
		
		when(userService.createUser(any(User.class))).thenReturn(user1);
		
		this.mockMvc.perform(post("/api/v1/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user1)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", is(user1.getName())))
		.andExpect(jsonPath("$.email", is(user1.getEmail())))
		.andExpect(jsonPath("$.employee_id", is(user1.getEmployeeId())))
		.andExpect(jsonPath("$.password", is(user1.getPassword())))
		.andExpect(jsonPath("$.type", is(user1.getType())));
			
	}
	
//	@Test
//	void shouldFetchAllMovies() throws Exception {
//		
//		List<Movie> list = new ArrayList<>();
//		list.add(avatarMovie);
//		list.add(titanicMovie);
//		
//		when(movieService.getAllMovies()).thenReturn(list);
//		
//		this.mockMvc.perform(get("/movies"))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.size()", is(list.size())));
//	}
//	
//	@Test
//	void shouldFetchOneMovieById() throws Exception {
//		
//		when(movieService.getMovieById(anyLong())).thenReturn(avatarMovie);
//		
//		this.mockMvc.perform(get("/movies/{id}", 1L))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.name", is(avatarMovie.getName())))
//			.andExpect(jsonPath("$.genera", is(avatarMovie.getGenera())));
//	}
//	
//	@Test
//	void shouldDeleteMovie() throws Exception {
//		
//		doNothing().when(movieService).deleteMovie(anyLong());
//		
//		this.mockMvc.perform(delete("/movies/{id}", 1L))
//			.andExpect(status().isNoContent());
//			
//	}
//	
//	@Test
//	void shouldUpdateMovie() throws Exception {
//		
//		when(movieService.updateMovie(any(Movie.class), anyLong())).thenReturn(avatarMovie);		
//		this.mockMvc.perform(put("/movies/{id}", 1L)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(avatarMovie)))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.name", is(avatarMovie.getName())))
//		.andExpect(jsonPath("$.genera", is(avatarMovie.getGenera())));
//	}
}