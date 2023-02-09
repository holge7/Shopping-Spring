package holge.shopping.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commons.dto.ApiResponse;
import holge.shopping.userservice.dto.LoginRequest;
import holge.shopping.userservice.dto.RegisterRequest;
import holge.shopping.userservice.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserControllerImpl implements UserController{
	
	UserService userService;
	
	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest login) {
		ApiResponse response = new ApiResponse(false, "", userService.login(login.getEmail(), login.getPassword()));
	
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(
				@RequestBody RegisterRequest register
			) {
		ApiResponse response = new ApiResponse(false, "", userService.register(
				register.getName(), 
				register.getEmail(), 
				register.getRol(), 
				register.getPassword()));
	
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	
}
