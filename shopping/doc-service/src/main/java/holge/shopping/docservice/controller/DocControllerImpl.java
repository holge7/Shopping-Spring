package holge.shopping.docservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import commons.dto.ApiResponse;
import holge.shopping.docservice.service.DocServiceImpl;

@RestController
@RequestMapping("/api/doc")
public class DocControllerImpl {
	
	public DocServiceImpl docService;
	
	public DocControllerImpl(DocServiceImpl docService) {
		this.docService = docService;
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse>  swaggerTest() {
		return new ResponseEntity<ApiResponse>(
				docService.getSwaggerURLs(),
				HttpStatus.OK
			);
	}
	
}
