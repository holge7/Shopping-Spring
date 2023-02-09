package holge.shopping.inventoryservice.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commons.dto.ApiResponse;
import holge.shopping.inventoryservice.entity.Product;
import holge.shopping.inventoryservice.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/product")
public class ProductControllerImpl implements ProductController {
	
	ProductService productService;
	
	public ProductControllerImpl(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("test")
	public String test() {
		return "Hello World!!";
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> findProduct(
			@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
			@PathVariable(value = "id") Long id ) {
		ApiResponse response = new ApiResponse(false, "", productService.findProductByID(id));
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@GetMapping()
	public ResponseEntity<ApiResponse> findProductByName(
			@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam (value = "name") String name) {
		ApiResponse response = new ApiResponse(false, "", productService.findProductByName(name, page, size));
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<ApiResponse> filterByPriceGreaterThan(
			@RequestParam (value = "priceMin", required = false, defaultValue = "0") Optional<Double> priceMin,
			@RequestParam (value = "priceMax", required = false, defaultValue = "9999999") Optional<Double> priceMax,
			@RequestParam (value = "name", required = true) String name,
			@RequestParam (value = "page", required = false, defaultValue = "0") int page,
			@RequestParam (value = "size", required = false, defaultValue = "20") int size
		) {
		
		ApiResponse response;
		
		//We need at least one price to do the filter
		if (!priceMin.isPresent() && !priceMax.isPresent()) {
			response = new ApiResponse("At least one filtering price is necessary");
			return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.BAD_REQUEST
				);
		}

		response = new ApiResponse(false, "", productService.findByPriceBetween(name, priceMin, priceMax, page, size));
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product product) {
		ApiResponse response = new ApiResponse(false, "", productService.createProduct(product)); 
		return new ResponseEntity<ApiResponse>(
				response,
				HttpStatus.CREATED
			);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable(value = "id") Long id) {
		ApiResponse response = new ApiResponse(false, "", productService.deleteProduct(id));
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}

	@PutMapping
	public ResponseEntity<ApiResponse> editProduct(@Valid Product product) {
		ApiResponse response = new ApiResponse(false, "", productService.editProduct(product));
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	

}
