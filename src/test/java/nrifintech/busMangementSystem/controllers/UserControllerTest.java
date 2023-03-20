package nrifintech.busMangementSystem.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

	@Test
	public void testGetAlluser() {
		List<User> userList = new ArrayList<>();
		when(userService.getUser()).thenReturn(userList);

		ResponseEntity<List<User>> responseEntity = userController.getAlluser();

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testGetUserById() {
		User user = new User();
		when(userService.getUser(ArgumentMatchers.anyInt())).thenReturn(user);

		ResponseEntity<UserDto> responseEntity = userController.getUserById(null, 1);

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testCreateUser() {
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
		when(userService.checkUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true);

		ResponseEntity<ApiResponse> responseEntity = (ResponseEntity<ApiResponse>) userController.userLogin("test@test.com", "password");

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testAdminLogin() {
		when(userService.checkAdmin(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true);

		ResponseEntity<ApiResponse> responseEntity = (ResponseEntity<ApiResponse>) userController.adminLogin("test@test.com", "password");

		assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}
}
