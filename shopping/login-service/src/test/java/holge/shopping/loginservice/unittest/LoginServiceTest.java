package holge.shopping.loginservice.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import commons.dto.ApiResponse;
import commons.dto.UserDTO;
import commons.enums.ERol;
import holge.shopping.loginservice.exception.CustomGenericalException;
import holge.shopping.loginservice.payload.LoginResponse;
import holge.shopping.loginservice.security.JwtUtils;
import holge.shopping.loginservice.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
	
	/*
	 * VARS
	 */
	private LoginService loginService;
	private JwtUtils jwtUtils;
	private Gson gson;
	
	String jwtSecret = "jwtKeyWithAlmost512bitesToABestProtectionMyNameIsHolgeAndIAmDoingTestsingThisKeyIsTooLongIAmBoring";
    private final String email = "test@test.com";
    private final String name = "testName";
    private final String password = "testPassword";
    private final Set<ERol> rolSet = new HashSet<ERol>(Arrays.asList(ERol.ROLE_ADMIN));
	
	/*
	 * MOCKS
	 */
	  static RestTemplate restTemplate = mock(RestTemplate.class);

	  
	/*
	 * PREPARE
	 */
	
	@BeforeEach
	public void tearUp() {	
		this.gson = new Gson();
		this.jwtUtils = new JwtUtils(gson);
		this.loginService = new LoginService(this.jwtUtils, LoginServiceTest.restTemplate, this.gson);
		
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
	public void loginSuccess() {
		UserDTO userDTO = new UserDTO(email, name, rolSet);
	    ApiResponse apiResponse = new ApiResponse(false, "", userDTO);
	    String apiResponseJson = new Gson().toJson(apiResponse);
	    
		HashMap<String, String> user = new HashMap<>();
		user.put("email", email);
		user.put("password", password);
		
	    
	    when(restTemplate.postForEntity("http://user-service/api/user/login", user, String.class))
	      .thenReturn(new ResponseEntity<>(apiResponseJson, HttpStatus.OK));

	    ApiResponse result = loginService.login(email, password);
	   
	    assertEquals(false, result.isError());
	    
	    String jwt = ((LoginResponse) result.getData()).getJwtToken();
	    assertTrue(jwtUtils.validateJwtToken(jwt));

	    assertEquals(email, ((LoginResponse) result.getData()).getEmail());
	    assertEquals(name, ((LoginResponse) result.getData()).getName());
	    assertEquals(rolSet, ((LoginResponse) result.getData()).getRol());

	}
	
	@Test
	public void loginError() {
	    ApiResponse apiResponse = new ApiResponse(true, "User not exists", null);
	    String apiResponseJson = new Gson().toJson(apiResponse);
	    
		HashMap<String, String> user = new HashMap<>();
		user.put("email", email);
		user.put("password", password);
		
	    when(restTemplate.postForEntity("http://user-service/api/user/login", user, String.class))
	      .thenReturn(new ResponseEntity<>(apiResponseJson, HttpStatus.OK));
	    
	    assertThrows(CustomGenericalException.class, () -> {
	    	loginService.login(email, password);
	    });
	}
	
}
