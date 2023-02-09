package holge.shopping.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import holge.shopping.inventoryservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findByName(String name, Pageable pageable);

	Page<Product> findByPriceBetween(Optional<Double> min, Optional<Double> max, Pageable pageable);
	
	Page<Product> findByPriceGreaterThanEqualAndNameLike(double price, String name, Pageable pageable);
	
	
}
