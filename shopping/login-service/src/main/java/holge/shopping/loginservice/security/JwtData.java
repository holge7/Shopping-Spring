package holge.shopping.loginservice.security;

import java.util.List;
import java.util.Set;

import commons.enums.ERol;

/**
 * DTO to store JWT body
 * @author Holge
 */
public class JwtData {
	
	String email;
	String name;
	Set<ERol> rol;
	
	public JwtData() {}

	public JwtData(String email, String name, Set<ERol> rol) {
		this.email = email;
		this.name = name;
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ERol> getRol() {
		return rol;
	}
	
	public void setRol(Set<ERol> rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "JwtData [email=" + email + ", name=" + name + ", rol=" + rol + "]";
	}
	
}
