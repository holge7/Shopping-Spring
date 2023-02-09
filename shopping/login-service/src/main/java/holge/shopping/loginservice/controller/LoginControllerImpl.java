package holge.shopping.loginservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commons.dto.ApiResponse;
import holge.shopping.loginservice.service.LoginService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/")
public class LoginControllerImpl implements LoginController {
	
	LoginService loginService;
	
	public LoginControllerImpl(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody String email,
			@RequestBody String password) {
		return new ResponseEntity<ApiResponse>(
					loginService.login(email, password),
					HttpStatus.OK
				);
	}
		
}
