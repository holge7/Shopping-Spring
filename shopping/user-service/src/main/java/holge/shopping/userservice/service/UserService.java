package holge.shopping.userservice.service;

import java.util.Set;

import commons.dto.UserDTO;
import commons.enums.ERol;

public interface UserService {
	
	/**
	 * Validate if a user/password exists
	 * @param email
	 * @param password
	 * @return UserDTO
	 */
	public UserDTO login(String email, String password);
	
	/**
	 * Delete a user by id
	 * @param id
	 * @return
	 */
	public boolean delete(Long id);
	
	/**
	 * Register a new user
	 * @param name
	 * @param email
	 * @param rol
	 * @param password
	 * @return UserDTO
	 */
	public UserDTO register(String name, String email, Set<ERol> rol, String password);
	
}
