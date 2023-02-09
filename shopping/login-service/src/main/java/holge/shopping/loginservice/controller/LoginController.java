package holge.shopping.loginservice.controller;

import org.springframework.http.ResponseEntity;

import holge.shopping.loginservice.payload.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface LoginController {
	
	@Operation(
			summary = "Login with `email` and `password`", 
			description = "Returns userDTO and JWT", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns `userDTO` and `JWT` (LoginResponse)",
							content = @Content(schema = @Schema(implementation = LoginResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "404", 
							description = "User not found", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal server error", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.dto.ApiResponse> login(
			@RequestBody String email,
			@RequestBody String password);
	
	
}
