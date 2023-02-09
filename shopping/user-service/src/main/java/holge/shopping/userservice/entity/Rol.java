package holge.shopping.userservice.entity;

import commons.enums.ERol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Enumerated(EnumType.STRING)
	public ERol rol;

	public Rol() {}
	
	public Rol(ERol rol) {
		this.rol = rol;
	}

	public Rol(long id, ERol rol) {
		this.id = id;
		this.rol = rol;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ERol getRol() {
		return rol;
	}

	public void setRol(ERol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", rol=" + rol + "]";
	}
	
	
	
}
