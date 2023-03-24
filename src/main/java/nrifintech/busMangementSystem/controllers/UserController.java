package nrifintech.busMangementSystem.controllers;



import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import nrifintech.busMangementSystem.Service.impl.MailService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.UnauthorizedAction;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.payloads.UserDto;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private MailService mailService;

	// get
	@GetMapping("/get")
	public ResponseEntity<List<User>> getAlluser() {
		return ResponseEntity.ok(this.userService.getUser());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int uid) {
 // HttpServletRequest request param to check for jwt token
		//		Enumeration<String> headerNames = request.getHeaderNames();
//		System.out.println("user  sdsssssssssssssssssssss \n");
//		while (headerNames.hasMoreElements()) {
//			String headerName = headerNames.nextElement();
//			System.out.println(headerName + ": " + request.getHeader(headerName));
//		}
		return ResponseEntity.ok(this.userService.getUser(uid));
	}

	// post
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	ResponseEntity<User> createUser(@Valid @RequestBody User user) throws MessagingException {
//		Enumeration<String> headerNames = request.getHeaderNames();
//    	System.out.println("user create resolve \n");
//        while(headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }
		User createdUser = userService.createUser(user);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	@PostMapping(path = "/createAdmin", consumes = "application/json", produces = "application/json")
	ResponseEntity<User> createAdmin(HttpServletRequest request,@Valid @RequestBody User user) {
		Enumeration<String> headerNames = request.getHeaderNames();
    	System.out.println("user create resolve \n");
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
		user.setType(1);
		User createdUser = userService.createUser(user);
		user.setType(1);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	// update
	@PostMapping("/update/{userId}")
	ResponseEntity<User> createUser(@RequestBody User user, @PathVariable("userId") int userId) {
		User updatedUser = userService.updateUser(user, userId);
		return ResponseEntity.ok(updatedUser);
	}

	// delete
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("user deleted", true), HttpStatus.OK);
	}

	@GetMapping("/employee/login/{email}/{password}")
	public ResponseEntity<?> userLogin(HttpServletRequest request, @PathVariable("email") String email, @PathVariable("password") String password) {
		boolean isAuthenticated = userService.checkUser(email, password);

		if (isAuthenticated) {
			User user = userService.getUserByEmail(email);
			String token = jwtTokenUtil.generateToken(user.getId(), user.getType()); // generate JWT token

			// include JWT token in the response
			Map<String, Object> responseBody = new HashMap<>();
			responseBody.put("token", token);
			responseBody.put("userId", user.getId());
			responseBody.put("message", "Authenticated success");
			responseBody.put("success", true);
			
			HttpSession session = request.getSession();

			return ResponseEntity.ok(responseBody);
		} else {
			return new ResponseEntity(new ApiResponse("User not found or password is incorrect!", false),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/admin/login/{email}/{password}")
	public ResponseEntity<?> adminLogin(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		boolean isAuthenticated = userService.checkAdmin(email, password);
		if (isAuthenticated) {
			User user = userService.getAdminByEmail(email);
			if(user.getType()==0) throw new UnauthorizedAction(email, "trying to login");
			String token = jwtTokenUtil.generateToken(user.getId(), 1); // generate JWT token
			System.out.println(email + "\n\n-------\n\n");
			// include JWT token in the response
			Map<String, Object> responseBody = new HashMap<>();
			responseBody.put("token", token);
			responseBody.put("message", "Authenticated success");
			responseBody.put("success", true);

			return ResponseEntity.ok(responseBody);
		} else {
			return new ResponseEntity(new ApiResponse("User not found or password is incorrect!", false),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<?> ValidateToken(@PathVariable("token") String token) {
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		return new ResponseEntity(new ApiResponse("token validated", true), HttpStatus.OK);
	}
}
