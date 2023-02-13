package holge.shopping.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import commons.dto.UserDTO;
import holge.shopping.userservice.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface UserControllerAdmin {
	
	@Operation(
			summary = "Register a new user with any rol", 
			description = "Endpoint to register user for admins users", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns `userDTO` and `JWT` for the user auth",
							content = @Content(schema = @Schema(implementation = UserDTO.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "409", 
							description = "Conflict", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal server error", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.dto.ApiResponse> register(
			@RequestBody RegisterRequest register
		);
	
	@Operation(
			summary = "Delete a user by ID", 
			description = "Endpoint to delete user as user admins users, return Api response with true data value if could delete the user", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns `userDTO` and `JWT` for the user auth",
							content = @Content(schema = @Schema(implementation = UserDTO.class))),
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
	public ResponseEntity<commons.dto.ApiResponse> delete(
			@RequestParam Long id
		);
	
}
