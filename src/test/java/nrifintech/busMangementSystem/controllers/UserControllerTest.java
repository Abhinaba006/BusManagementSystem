package nrifintech.busMangementSystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nrifintech.busMangementSystem.JwtTokenUtil;
import nrifintech.busMangementSystem.Service.impl.MailService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.payloads.UserDto;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;
	
	@Mock
    private MailService mailService;
	
	@Mock
	JwtTokenUtil jwtTokenUtil;

	@Test
	public void testGetAlluser() {
		List<User> userList = new ArrayList<>();
		when(userService.getUser()).thenReturn(userList);

		ResponseEntity<List<User>> responseEntity = userController.getAlluser();

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testGetUserById() {
		UserDto user = new UserDto();
		when(userService.getUser(ArgumentMatchers.anyInt())).thenReturn(user);

		ResponseEntity<UserDto> responseEntity = userController.getUserById(1);

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testCreateUser() throws MessagingException {
		User user = new User();
		when(userService.createUser(user)).thenReturn(user);

		ResponseEntity<User> responseEntity = userController.createUser(user);

		assert(responseEntity.getStatusCode().equals(HttpStatus.CREATED));
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		when(userService.updateUser(user, 1)).thenReturn(user);

		ResponseEntity<User> responseEntity = userController.createUser(user, 1);

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testDeleteUser() {
		ResponseEntity<ApiResponse> responseEntity = (ResponseEntity<ApiResponse>) userController.deleteUser(1);

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testUserLogin() {
		// Mock user service to return true for checkUser method
		when(userService.checkUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true);
		
		// Create a mock user object and set its email and password
		User user = new User();
		user.setEmail("test@test.com");
		user.setPassword("password");
		
		// Mock user service to return the user object for getUserByEmail method
		when(userService.getUserByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		
		// Call userLogin method with mock request and user email and password
		ResponseEntity<?> responseEntity = userController.userLogin(mock(HttpServletRequest.class), "test@test.com", "password");
		
		// Assert that response status is OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		// Assert that response body contains a success message and a JWT token
		Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
		assertTrue(responseBody.containsKey("success"));
		assertTrue(responseBody.containsKey("token"));
		assertTrue(responseBody.containsKey("message"));
		assertTrue((Boolean) responseBody.get("success"));
		assertEquals("Authenticated success", responseBody.get("message"));
	}


	@Test
	public void testAdminLogin() {
	    // Arrange
	    String email = "admin@test.com";
	    String password = "password";
	    User user = new User();
	    user.setId((int) 1L);
	    user.setEmail(email);
	    user.setPassword(password);
	    user.setType(1);
	    when(userService.checkAdmin(email, password)).thenReturn(true);
	    when(userService.getAdminByEmail(email)).thenReturn(user);

	    // Act
	    ResponseEntity<?> response = userController.adminLogin(email, password);

	    // Assert
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertNotNull(response.getBody());
	    assertTrue(response.getBody() instanceof Map);

	    Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
	    assertEquals("Authenticated success", responseBody.get("message"));
	    assertEquals(true, responseBody.get("success"));
	    assertTrue(responseBody.containsKey("token"));
	    String token = (String) responseBody.get("token");
	    //assertTrue(jwtTokenUtil.validateToken(token, user.getId(), 1));
	}



}
