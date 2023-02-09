package holge.shopping.inventoryservice.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import holge.shopping.inventoryservice.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

public interface ProductController {
	
	@Operation(
			summary = "Find product by ID", 
			description = "Returns API response with the product or null if we dont have any product with it ID", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns Product or null",
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.dto.ApiResponse> findProduct(
				@RequestParam(required = false, defaultValue = "1") int page,
	            @RequestParam(required = false, defaultValue = "10") int size,
				@PathVariable(value = "id") Long id
			);
	
	@Operation(
			summary = "Find product by NAME", 
			description = "Returns API response with the product or null if we dont have any product with it NAME", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns Product or null",
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	ResponseEntity<commons.dto.ApiResponse> findProductByName(
				@RequestParam(required = false, defaultValue = "1") int page,
	            @RequestParam(required = false, defaultValue = "20") int size,
				@RequestParam (value = "name") String name
			);
	
	@Operation(
			summary = "Filter product by Price", 
			description = "Returns API response with a list of products filter by price and name", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns Product or null",
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.dto.ApiResponse> filterByPriceGreaterThan(
				@RequestParam (value = "priceMin", required = false, defaultValue = "0") Optional<Double> priceMin,
				@RequestParam (value = "priceMax", required = false, defaultValue = "9999999") Optional<Double> priceMax,
				@RequestParam (value = "name", required = true) String name,
				@RequestParam (value = "page", required = true) int page,
				@RequestParam (value = "size", required = true) int size
			);
	
	@Operation(
			summary = "Create a new Product", 
			description = "Create a new Product, this product cant be named as another existed product", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns Product",
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request, returned data failed", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.dto.ApiResponse> createProduct(@Valid @RequestBody Product product);
	
	@Operation(
			summary = "Delete Product by ID", 
			description = "Delete product", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Delete OK",
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request, returned data failed", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.dto.ApiResponse> deleteProduct(@PathVariable(value = "id") Long id);
	
	
	@Operation(
			summary = "Edit an existing product", 
			description = "Edit product", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Edit OK",
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request, returned data failed", 
							content = @Content(schema = @Schema(implementation = commons.dto.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.dto.ApiResponse> editProduct(@Valid @RequestBody Product product);
	
}
