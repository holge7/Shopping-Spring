package holge.shopping.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commons.dto.ApiResponse;
import holge.shopping.userservice.config.PathConstants;
import holge.shopping.userservice.dto.RegisterRequest;
import holge.shopping.userservice.service.UserServiceImpl;

@RestController
@RequestMapping(PathConstants.API_RESOURCE_STRING+PathConstants.USER_RESOURCE_STRING + PathConstants.ADMIN_RESOURCE_STRING)
public class UserControllerAdminImpl implements UserControllerAdmin {
	UserServiceImpl userService;
	
	public UserControllerAdminImpl(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("")
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
	
	@DeleteMapping("")
	public ResponseEntity<ApiResponse> delete(
			@RequestParam Long id
			) {
		boolean result = userService.delete(id);
		ApiResponse response = new ApiResponse(result, "", null);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}

}
