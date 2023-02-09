package holge.shopping.userservice.dto;

import java.util.Set;

import commons.enums.ERol;

public class RegisterRequest {
	
	String name;
	String email;
	Set<ERol> rol;
	String password;
	
	public RegisterRequest() {}
	
	public RegisterRequest(String name, String email, Set<ERol> rol, String password) {
		this.name = name;
		this.email = email;
		this.rol = rol;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterRequest [name=" + name + ", email=" + email + ", rol=" + rol + ", password=" + password + "]";
	}
	
}
