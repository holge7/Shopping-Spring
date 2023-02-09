package holge.shopping.loginservice.payload;
import java.util.Set;

import commons.enums.ERol;

public class LoginResponse {
	
	String jwtToken;
	String name;
	String email;
	Set<ERol> rol;
	
	public LoginResponse() {}
	
	public LoginResponse(String jwtToken, String name, String email, Set<ERol> rol) {
		this.jwtToken = jwtToken;
		this.name = name;
		this.email = email;
		this.rol = rol;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<ERol> getRol() {
		return rol;
	}

	public void setRol(Set<ERol> rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "LoginResponse [jwtToken=" + jwtToken + ", name=" + name + ", email=" + email + ", rol=" + rol + "]";
	}
	
	
	
}
