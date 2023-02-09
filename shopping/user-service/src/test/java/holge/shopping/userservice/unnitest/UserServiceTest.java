package holge.shopping.userservice.unnitest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import commons.dto.UserDTO;
import commons.enums.ERol;
import holge.shopping.userservice.entity.Rol;
import holge.shopping.userservice.entity.User;
import holge.shopping.userservice.exception.RolNotFoundException;
import holge.shopping.userservice.exception.UserAlreadyExistsException;
import holge.shopping.userservice.exception.UserNotFoundException;
import holge.shopping.userservice.mapper.UserMapper;
import holge.shopping.userservice.repository.RolRepository;
import holge.shopping.userservice.repository.UserRepository;
import holge.shopping.userservice.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	/*
	 * VARS
	 */
	
	UserService userService;
    static Long id = 0L;
    static String email = "jorge@gmail.com";
    static String name = "Holge";
    static String password = "password";
    static String encodePassword = "encode_password";
    static String emailAlreadyRegister = "already@register.com";

    static List<String> rolesList = Arrays.asList("ROLE_USER");
    
    static Rol rol_user = new Rol(1, ERol.ROLE_USER);
    static Rol rol_moderator = new Rol(2, ERol.ROLE_MODERATOR);
    static Rol rol_admin = new Rol(3, ERol.ROLE_ADMIN);
    
    static Set<ERol> eRolesSet = new HashSet<>(Arrays.asList(ERol.ROLE_USER));
    static Set<Rol> rolesSet = new HashSet<>(Arrays.asList(rol_user));
	
	/*
	 * MOCKS
	 */
    static User user = mock(User.class);
    static UserDTO userDTO = mock(UserDTO.class);
    static UserRepository userRepository = mock(UserRepository.class);
    static RolRepository rolRepository = mock(RolRepository.class);
    static PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    static UserMapper userMapper = mock(UserMapper.class);
    
    
	/*
	 * PREPARE
	 */
	
	@BeforeEach
	public void tearUp() {
		
		// USER DTO
		when(userDTO.getEmail()).thenReturn(email);
		when(userDTO.getName()).thenReturn(name);
		when(userDTO.getRol()).thenReturn(eRolesSet);
		
		// USER
        when(user.getId()).thenReturn(id);
        when(user.getEmail()).thenReturn(email);
        when(user.getName()).thenReturn(name);
        when(user.getPassword()).thenReturn(encodePassword);
        when(user.getRol()).thenReturn(rolesSet);
        
        // ROL
        
		
        // USER REPOSITORY
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        
        // ROL REPOSITORY
        when(rolRepository.findByRol(ERol.ROLE_USER)).thenReturn(Optional.of(rol_user));
        when(rolRepository.findByRol(ERol.ROLE_MODERATOR)).thenReturn(Optional.of(rol_moderator));
        when(rolRepository.findByRol(ERol.ROLE_ADMIN)).thenReturn(Optional.of(rol_admin));
        
        // USER MAPPER
        when(userMapper.toDto(user)).thenReturn(userDTO);
        
        // PASSWORD ENCODER
        when(passwordEncoder.matches(password, encodePassword)).thenReturn(true);
        when(passwordEncoder.encode(password)).thenReturn(encodePassword);
        
        
        userService = new UserService(userRepository, rolRepository, passwordEncoder, userMapper);
		
	}
	
	@AfterEach
	public void tearDown() {
		
	}
	
	/*
	 * TESTS
	 */
	
	// LOGIN
	@Test
	public void loginGood() {
		UserDTO userDTORequest = userService.login(email, password);
		assertEquals(userDTO, userDTORequest);
	}
	
	@Test
	public void login_when_password_not_match() {
		// Given 
		String badPassword = "badPassword";
		when(passwordEncoder.matches(badPassword, encodePassword)).thenReturn(false);
		
		// When
		assertThrows(UserNotFoundException.class, () -> {
			userService.login(email, badPassword);			
		});
	}
	
	@Test
	public void login_when_email_not_exists() {
		// Given
		String badEmail = "badEmail@gmail.com";
		when(userRepository.findByEmail(badEmail)).thenReturn(Optional.empty());
		
		// When
		assertThrows(UserNotFoundException.class, () -> {
			userService.login(badEmail, encodePassword);
		});
		
	}
	
	
	// REGISTER
	
	@Test
	public void register_with_multiples_roles() {
		// USER DTO
		Set<ERol> eRolesSet = new HashSet<>(Arrays.asList(ERol.ROLE_MODERATOR, ERol.ROLE_ADMIN));
		when(userDTO.getEmail()).thenReturn(email);
		when(userDTO.getName()).thenReturn(name);
		when(userDTO.getRol()).thenReturn(eRolesSet);
		
		// USER
		Set<Rol> rolesSet = new HashSet<>(Arrays.asList(rol_moderator, rol_admin));
        when(user.getId()).thenReturn(id);
        when(user.getEmail()).thenReturn(email);
        when(user.getName()).thenReturn(name);
        when(user.getPassword()).thenReturn(encodePassword);
        when(user.getRol()).thenReturn(rolesSet);
        
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);
        
		UserDTO userSaved = userService.register(user.getName(), user.getEmail(), eRolesSet, password);
		assertEquals(userDTO, userSaved);
	}
	
	@Test
	public void register_with_default_role() {
		when(userRepository.existsByEmail(email)).thenReturn(false);
		when(rolRepository.findByRol(ERol.ROLE_USER)).thenReturn(Optional.of(rol_user));
		when(passwordEncoder.encode(password)).thenReturn(encodePassword);
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		UserDTO userDTOsaved = userService.register(name, email, null, password);
		
		assertNotNull(userDTOsaved);
		assertEquals(userDTOsaved, userDTO);
	}
	
	@Test
	public void register_already_email() {
		String alreadyRegisterEmail = "already@gmail.com";
		when(userRepository.existsByEmail(alreadyRegisterEmail)).thenReturn(true);
		
		assertThrows(UserAlreadyExistsException.class, () -> {
			userService.register(name, alreadyRegisterEmail, eRolesSet, encodePassword);
		});
	}
	
	@Test
	public void register_with_invalid_role() {
		when(userRepository.existsByEmail(email)).thenReturn(false);
		when(rolRepository.findByRol(ERol.ROLE_USER)).thenReturn(Optional.empty());
		
		Set<ERol> eRolSet = new HashSet<>(Arrays.asList(ERol.ROLE_USER));
		
		assertThrows(RolNotFoundException.class, () -> {
			userService.register(name, email, eRolSet, encodePassword);
		});
		
		
	}
	
	
}
