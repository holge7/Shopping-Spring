package holge.shopping.loginservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import commons.dto.ApiResponse;

@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerImpl.class);
	
	@ExceptionHandler(CustomGenericalException.class)
	public ResponseEntity<ApiResponse> handleException(CustomGenericalException e) {
		ApiResponse response = new ApiResponse(e.getMessage());
		return new ResponseEntity<ApiResponse>(
					response,
					e.getHttpStatus()
				);
	}
	
	
}
