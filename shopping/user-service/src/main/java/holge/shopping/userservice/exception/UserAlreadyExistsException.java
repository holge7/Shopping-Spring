package holge.shopping.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg;
	HttpStatus httpStatus;
	
    public UserAlreadyExistsException(String message) {
        super(message);
        this.msg = message;
        this.httpStatus = HttpStatus.CONFLICT;
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
