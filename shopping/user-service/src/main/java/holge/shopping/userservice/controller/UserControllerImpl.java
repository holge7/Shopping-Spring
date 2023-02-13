package holge.shopping.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commons.dto.ApiResponse;
import holge.shopping.userservice.dto.LoginRequest;
import holge.shopping.userservice.dto.RegisterRequest;
import holge.shopping.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("api/user")
public class UserControllerImpl implements UserController{
	
	UserService userService;
	
	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	
	@PostMapping("/login")
	@CircuitBreaker(name = "default")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest login) {
		ApiResponse response = new ApiResponse(false, "", userService.login(login.getEmail(), login.getPassword()));
	
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@PostMapping("")
	public ResponseEntity<ApiResponse> register(
				@RequestParam String name,
				@RequestParam String email,
				@RequestParam String password
			) {
		ApiResponse response = new ApiResponse(false, "", userService.register(
				name, 
				email, 
				null, 
				password));
	
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	
}
