package nrifintech.busMangementSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.UserService;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	 
	//get
	@GetMapping("/")
	public ResponseEntity<List<User>> getAlluser(){
		return ResponseEntity.ok(this.userService.getUser());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int uid){
		return ResponseEntity.ok(this.userService.getUser(uid));
	}
	//post
	
	@PostMapping("/")
	ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	//update
	@PostMapping("/{userId}")
	ResponseEntity<User> createUser(@RequestBody User user, @PathVariable("userId") int userId){
		User updatedUser = userService.updateUser(user, userId);
		return ResponseEntity.ok(updatedUser);
	}
	//delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId){
		userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("user deleted", true), HttpStatus.OK);
	}
}
