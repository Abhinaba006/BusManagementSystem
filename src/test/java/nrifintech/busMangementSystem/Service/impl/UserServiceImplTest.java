package nrifintech.busMangementSystem.Service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.payloads.UserDto;
import nrifintech.busMangementSystem.repositories.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Spy
    private ModelMapper modelMapper;
	
	@Mock
    private UserRepo userRepo;

	@InjectMocks
    private UserServiceImpl userService;
    
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	User user;
	
    @BeforeEach
    void setup()
    {
    	System.out.println("Setting up");
    	user = new User();
        user.setName("Demo user");
        user.setEmail("demo12@gmail.com");
        user.setPassword("demo123");
        user.setType(0);
    }
    

	
	@Test
	void testCreateUser() {
        Mockito.when(this.userRepo.save(user)).thenReturn(user);
        User result = this.userService.createUser(user);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Demo user", result.getName());
        assertEquals("demo12@gmail.com", result.getEmail());
	}

	@Test
	void testUpdateUser() {
		//creating exisitng and updated user
		User existingUser  = new User();
		existingUser.setEmail("demo12@gmail.com");
		existingUser.setName("demo user");
		existingUser.setPassword("demo123");
		existingUser.setType(1);
		
		User updatedUser  = new User();
		updatedUser.setEmail("temp12@gmail.com");
		updatedUser.setName("temp user");
		updatedUser.setPassword("temp123");
		updatedUser.setType(1);
		
		//mockito implementations.
		when(userRepo.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
		when(userRepo.save(existingUser)).thenReturn(updatedUser);
		
		User result= userService.updateUser(updatedUser,existingUser.getId());
		assertEquals(updatedUser.getEmail(),result.getEmail());
		assertEquals(updatedUser.getPassword(),result.getPassword());
	}

	@Test
	void testGetUserInt() {		
		//create another user.
		User user2 = new User();
		user2.setEmail("temp12@gmail.com");
		user2.setPassword("temp123");
		user2.setName("temp user");
		user2.setType(0);
		
		//mockito
		when(userRepo.findById(user2.getId())).thenReturn(Optional.of(user2));
		UserDto result = userService.getUser(user2.getId());
		assertEquals(user2.getName(),result.getName());
		
	}

	@Test
	void testGetUser() {
		//get all user
		List<User> users= new ArrayList<User>();

		//create another user.
		User user2 = new User();
		user2.setEmail("temp12@gmail.com");
		user2.setPassword("temp123");
		user2.setName("temp user");
		user2.setType(0);
		
		users.add(user);
		users.add(user2);
		
		when(userRepo.findAll()).thenReturn(users);
		
		List<User> result = userService.getUser();
		assertEquals(result.size(),users.size());
		
	}

	@Test
	void testDeleteUser() {
		when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));		
		userService.deleteUser(user.getId());
		verify(userRepo, times(1)).delete(user);
		
	}

	@Test
	void testCheckUser() {
		user.setPassword(passwordEncoder.encode("demo123"));
		when(userRepo.findByOnlyEmail("demo12@gmail.com")).thenReturn(Optional.of(user));
		boolean result = userService.checkUser(user.getEmail(), "demo123");
		assertTrue(result);
	}

	@Test
	void testCheckAdmin() {
		//create admin
		User admin = new User();
		admin.setEmail("admin12@gmail.com");
		admin.setPassword(passwordEncoder.encode("admin123"));
		admin.setName("admin");
		admin.setType(1);
		
		when(userRepo.findByEmail("admin12@gmail.com", 1)).thenReturn(Optional.of(admin));
		boolean result = userService.checkAdmin(admin.getEmail(), "admin123");
		assertTrue(result);
	}

}
