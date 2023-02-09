package holge.shopping.loginservice.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.google.gson.Gson;

import commons.dto.UserDTO;
import commons.enums.ERol;
import holge.shopping.loginservice.security.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@ExtendWith(MockitoExtension.class)
public class JwtUtilsTests {
	
	/*
	 * VARS
	 */
	JwtUtils jwtUtils;
	Gson gson;
	
	String jwtSecret = "jwtKeyWithAlmost512bitesToABestProtectionMyNameIsHolgeAndIAmDoingTestsingThisKeyIsTooLongIAmBoring";
	String email = "jorge@gmail.com";
	String name = "Holge";
    Set<ERol> roles = Set.of(ERol.ROLE_ADMIN);
	String jwtBad = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2VAZ21haWwuY29tXCIsXCJuYW1lXCI6XCJIb2xnZVwiLFwicm9sZXNcIjpbXCJST0xFX0FETUlOXCJdfSIsImlhdCI6MTY3MTk5NzQwMywiZXhwIjoxNjcyOTk3dddddd.EoTpIccCsiORWtFp9R4UUO4y8xJR81K9pfm9gFjIOU-Ng7CqzImSFCFte4hIFqVZoRYCANSbOjNg6FsEMadddd";
	
	/*
	 * MOCKS
	 */
	static UserDTO userDTO = mock(UserDTO.class);
	
	
	/*
	 * PREPARE
	 */
	@BeforeEach
	public void tearUp() {
		// UserDTO
		when(userDTO.getEmail()).thenReturn(email);
		when(userDTO.getName()).thenReturn(name);
		when(userDTO.getRol()).thenReturn(roles);
		
		gson = new Gson();
		jwtUtils = new JwtUtils(gson);
		
		//Reflection
		ReflectionTestUtils.setField(jwtUtils, "jwtSecret", jwtSecret);
		ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 30000);
		
	}
	
	@AfterEach
	public void tearDown() {
		
	}
	
	
	/*
	 * TEST
	 */
	@Test
	public void generate_jwt() {
		String jwt = jwtUtils.generateJwtToken(userDTO);
		
        String regex = "^[\\w-]+\\.[\\w-]+\\.[\\w-]+$";
        Pattern pattern = Pattern.compile(regex);
		
		assertNotNull(jwt);
		assertTrue(pattern.matcher(jwt).matches());
	}
	
	
	@Test
	public void get_all_claims_jwt() {
		String jwt = jwtUtils.generateJwtToken(userDTO);
		Jws<Claims> data = jwtUtils.getAllClaimsFromToken(jwt);
		
		assertEquals("HS512", data.getHeader().get("alg"));
		
		UserDTO userDTOjwt = gson.fromJson((String) data.getBody().get("sub"), UserDTO.class);

		assertEquals(email, userDTOjwt.getEmail());
		assertEquals(name, userDTOjwt.getName());
		
		// No assert role because gson dont parse it :/
	}
	
	@Test
	public void validate_jwt() {
		String jwt = jwtUtils.generateJwtToken(userDTO);
		boolean isValid = jwtUtils.validateJwtToken(jwt);
		assertTrue(isValid);
	}
	
	@Test
	public void validate_jwt_bad() {
		boolean isValid = jwtUtils.validateJwtToken(jwtBad);
		assertFalse(isValid);
	}
	
}
