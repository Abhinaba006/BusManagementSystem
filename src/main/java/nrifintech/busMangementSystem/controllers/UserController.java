package nrifintech.busMangementSystem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.JwtTokenUtil;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.payloads.UserDto;
import nrifintech.busMangementSystem.repositories.UserRepo;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	 
	//get
	@GetMapping("/get")
	public ResponseEntity<List<User>> getAlluser(){
		return ResponseEntity.ok(this.userService.getUser());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int uid){
		return ResponseEntity.ok(this.userService.getUser(uid));
	}
	//post
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	//update
	@PostMapping("/update/{userId}")
	ResponseEntity<User> createUser(@RequestBody User user, @PathVariable("userId") int userId){
		User updatedUser = userService.updateUser(user, userId);
		return ResponseEntity.ok(updatedUser);
	}
	//delete
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId){
		userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("user deleted", true), HttpStatus.OK);
	}

	@GetMapping("/employee/login/{email}/{password}")
	public ResponseEntity<?> userLogin(@PathVariable("email") String email, @PathVariable("password") String password){
		boolean isAuthenticated = userService.checkUser(email,password);
	
		if(isAuthenticated) {
			User user = userService.getUserByEmail(email);
	        String token = jwtTokenUtil.generateToken(user.getId(), 0);  // generate JWT token
	        
	        // include JWT token in the response
	        Map<String, Object> responseBody = new HashMap<>();
	        responseBody.put("token", token);
	        responseBody.put("message", "Authenticated success");
	        responseBody.put("success", true);

	        return ResponseEntity.ok(responseBody);
		}
		else {
			return new ResponseEntity(new ApiResponse("User not found or password is incorrect!",false), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/admin/login/{email}/{password}")
	public ResponseEntity<?> adminLogin(@PathVariable("email") String email, @PathVariable("password") String password){
		boolean isAuthenticated = userService.checkAdmin(email,password);
		if(isAuthenticated) {
			User user = userService.getAdminByEmail(email);
	        String token = jwtTokenUtil.generateToken(user.getId(), 1);  // generate JWT token
	        System.out.println(email+"\n\n-------\n\n");
	        // include JWT token in the response
	        Map<String, Object> responseBody = new HashMap<>();
	        responseBody.put("token", token);
	        responseBody.put("message", "Authenticated success");
	        responseBody.put("success", true);

	        return ResponseEntity.ok(responseBody);
		}
		else {
			return new ResponseEntity(new ApiResponse("User not found or password is incorrect!",false), HttpStatus.BAD_REQUEST);
		}
	}
}
