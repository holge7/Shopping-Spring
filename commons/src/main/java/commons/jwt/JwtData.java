package commons.jwt;

import java.util.List;

public class JwtData {
	
	String email;
	String name; 
	List<String> rol;
	
	public JwtData() {}

	public JwtData(String email, String name, List<String> rol) {
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

	public List<String> getRol() {
		return rol;
	}

	public void setRol(List<String> rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "JwtData [email=" + email + ", name="+name+" rol=" + rol + "]";
	}
	
}
