package holge.shopping.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg;
	HttpStatus httpStatus;
	
    public UserNotFoundException(String message) {
    	super(String.format("User [%s] not found", message));
    	this.msg = String.format("User [%s] not found", message);
    	this.httpStatus = HttpStatus.NOT_FOUND;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
    
    
    
}
