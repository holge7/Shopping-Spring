package holge.shopping.loginservice.exception;

import org.springframework.http.HttpStatus;

public class CustomGenericalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String msg;
	HttpStatus httpStatus;
	
	public CustomGenericalException(String msg, HttpStatus httpStatus) {
		this.msg = msg;
		this.httpStatus = httpStatus;
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
