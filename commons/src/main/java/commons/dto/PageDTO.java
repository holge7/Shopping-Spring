package commons.dto;

import java.util.List;

/**
 * Simple DTO class to have one structure when we want return a Pageable response
 * @author Jorge
 *
 */
public class PageDTO {
	
	Long totalItems;
	List<?> results;
	int totalPages;
	int currentPage;
	
	public PageDTO() {}
	
	public PageDTO(Long totalItems, List<?> results, int totalPages, int currentPage) {
		this.totalItems = totalItems;
		this.results = results;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
}
