package holge.shopping.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import holge.shopping.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
	
}
