package holge.shopping.inventoryservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.slf4j.Logger;
import commons.dto.ApiResponse;

@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {
	Logger log = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception e){
		ApiResponse response = new ApiResponse(e.getMessage());

		return new ResponseEntity<ApiResponse>(
			response, 
			HttpStatus.INTERNAL_SERVER_ERROR
		);

	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		((BindException) ex).getBindingResult().getAllErrors()
			.forEach((error) -> {
				String name = ((FieldError) error).getField();
				String msg = error.getDefaultMessage();
				errors.put(name, msg);
			});
		
		ApiResponse response = new ApiResponse(true, "", errors);
		
		return new ResponseEntity<>(
					response,
					HttpStatus.BAD_REQUEST
				);		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> handleException(DataIntegrityViolationException ex){
		log.error(ex.getMostSpecificCause().toString());
		ApiResponse response = new ApiResponse(ex.getMostSpecificCause().toString());

		return new ResponseEntity<ApiResponse>(
			response, 
			HttpStatus.INTERNAL_SERVER_ERROR
		);

	}
	
}
