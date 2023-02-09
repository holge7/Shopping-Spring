package commons.dto;

import java.util.Set;

import commons.enums.ERol;

public class UserDTO {
	public String email;
	public String name;
	public Set<ERol> rol;
	
	public UserDTO() {}
	
	public UserDTO(String email, String name, Set<ERol> rol) {
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
		return "UserDTO [email=" + email + ", name=" + name + ", rol=" + rol + "]";
	}
}
