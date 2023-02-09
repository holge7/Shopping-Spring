package holge.shopping.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import commons.enums.ERol;
import holge.shopping.userservice.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
	
	Optional<Rol> findByRol(ERol Rol);
	
}
