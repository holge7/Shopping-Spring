package holge.shopping.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commons.dto.ApiResponse;
import holge.shopping.userservice.dto.RegisterRequest;
import holge.shopping.userservice.service.UserService;

@RestController
@RequestMapping("api/admin/user/")
public class UserControllerAdminImpl {
	UserService userService;
	
	public UserControllerAdminImpl(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("register")
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
