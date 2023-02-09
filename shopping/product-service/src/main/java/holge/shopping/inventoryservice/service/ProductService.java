package holge.shopping.inventoryservice.service;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import commons.dto.PageDTO;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import holge.shopping.inventoryservice.entity.Product;
import holge.shopping.inventoryservice.exception.ProductNotFoundException;
import holge.shopping.inventoryservice.repository.ProductRepository;

@Service
public class ProductService {
	Logger log = LoggerFactory.getLogger(getClass());
	
	ProductRepository productRepository;
	
	public int MAX_ITEMS_PER_PAGE = 30;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public boolean deleteProduct(long id) {
		productRepository.deleteById(id);
		return true;
	}
	
	public Product editProduct(Product updatedProduct) {
		Optional<Product> optionalProduct = productRepository.findById(updatedProduct.getId());
		
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(updatedProduct.getName());
			product.setPrice(updatedProduct.getPrice());
			product.setDescription(updatedProduct.getDescription());
			return productRepository.save(product);	
		}
		
		throw new ProductNotFoundException("Product with id " + updatedProduct.getId() + " not found");
	}
	
	public Optional<Product> findProductByID(long id) {
		return productRepository.findById(id);
	}
	
	public PageDTO findProductByName(String name, int page, int size) {
		if (size > MAX_ITEMS_PER_PAGE) size = MAX_ITEMS_PER_PAGE;
		
		Pageable paging = PageRequest.of(page, size);
		Page<Product> pageProducts = productRepository.findByName(name, paging);
		
		return getPageDTO(pageProducts);
	}
	
	public PageDTO filterProductByPrice(double price, String name, int page, int size) {
		if (size > MAX_ITEMS_PER_PAGE) size = MAX_ITEMS_PER_PAGE;
		
		Pageable paging = PageRequest.of(page, size);
		Page<Product> pageProducts = productRepository.findByPriceGreaterThanEqualAndNameLike(price, name, paging);
		
		return getPageDTO(pageProducts);
	}

	public PageDTO findByPriceBetween(String name, Optional<Double> priceMin, Optional<Double> priceMax, int page, int size) {
		if (size > MAX_ITEMS_PER_PAGE) size = MAX_ITEMS_PER_PAGE;
		
		Pageable paging = PageRequest.of(page, size);
		Page<Product> pageProducts = productRepository.findByPriceBetween(priceMin, priceMax, paging);

	    return getPageDTO(pageProducts);
	}
	
	public PageDTO getPageDTO(Page<Product> pageProducts) {
		List<Product> products = pageProducts.getContent();
		
		return new PageDTO(
				pageProducts.getTotalElements(), 
				products, 
				pageProducts.getTotalPages(), 
				pageProducts.getNumber());
	}
	
	
}
