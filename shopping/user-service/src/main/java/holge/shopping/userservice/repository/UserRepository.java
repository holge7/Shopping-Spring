package holge.shopping.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import holge.shopping.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findById(Long id);
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
	
	void deleteById(Long id);
	
}
