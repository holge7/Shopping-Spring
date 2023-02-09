package commons.dto;

/**
 * Simple DTO class to send in all responses for owns apis
 * @author Jorge
 *
 */
public class ApiResponse {
	
	boolean error;
	String msg;
	Object data;
	
	public ApiResponse() {}
	
	public ApiResponse(boolean error, String msg, Object data) {
		this.error = error;
		this.msg = msg;
		this.data = data;
	}
	
	public ApiResponse(String msg) {
		this.error = true;
		this.msg = msg;
		this.data = null;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ApiResponse [error=" + error + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
	
	
}
