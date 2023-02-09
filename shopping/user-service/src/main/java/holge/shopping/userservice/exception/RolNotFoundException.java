package holge.shopping.userservice.exception;

import org.springframework.http.HttpStatus;

public class RolNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg;
	HttpStatus httpStatus;
	
    public RolNotFoundException(String message) {
    	super(String.format("Rol [%s] not found", message));
    	this.msg = String.format("Rol [%s] not found", message);
    	this.httpStatus = HttpStatus.NOT_FOUND;
    }

<<<<<<< HEAD
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

=======
<<<<<<< HEAD
>>>>>>> preview
=======
>>>>>>> develop
>>>>>>> develop
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
<<<<<<< HEAD
=======

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
<<<<<<< HEAD
>>>>>>> preview
=======
>>>>>>> develop
>>>>>>> develop
    
    
}
