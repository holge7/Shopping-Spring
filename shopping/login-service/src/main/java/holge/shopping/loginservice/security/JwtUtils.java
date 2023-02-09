package holge.shopping.loginservice.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import commons.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * Class to manage all JWT operations
 * @author Jorge
 *
 */
@Component
public class JwtUtils {
	private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${security.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${security.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	Gson gson;
	
	public JwtUtils(Gson gson) {
		this.gson = gson;
	}
	
	
	/**
	 * Get all info about a JWT
	 * @param jwt
	 * @return
	 */
	public UserDTO getUserFromJwt(String jwt) {
		// Get JWT payload
		int i = jwt.lastIndexOf('.');
		String withoutSignature = jwt.substring(0, i+1);
		Jwt<Header, Claims> untrusted = Jwts.parser()
				.parseClaimsJwt(withoutSignature);
		
		// Get userDTO from JWT payload
		String userString = untrusted.getBody().getSubject();
		
		//Parse user jwt data json to UserDTO
		UserDTO userDTO = gson.fromJson(userString, UserDTO.class);
		return userDTO;
		
		
		
	}
	
	/**
	 * Return Authentication class using JWT
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		// Get user
		UserDTO user = getUserFromJwt(token);
		
		// Set authorities of the User
		var authorities = user.getRol().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.toString()))
				.collect(Collectors.toList());
		
		// Return a authnetication with user and authorities
		return new UsernamePasswordAuthenticationToken(user, null, authorities);
		
	}
	
	
	/**
	 * Generate JWT
	 * @param userDTO
	 * @return
	 */
	public String generateJwtToken(UserDTO userDTO) {
		
		JwtData jwtData = new JwtData(userDTO.getEmail(), userDTO.getName(), userDTO.getRol());
		
		String jwtDataString = gson.toJson(jwtData);
		
		return Jwts.builder()
				.setSubject(jwtDataString)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	/**
	 * Get all info about a JWT
	 * @param authToken
	 * @return
	 */
	public Jws<Claims> getAllClaimsFromToken(String authToken) {
		// Change secret to signing ke from auth service
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(authToken);
	}
	
	
	/**
	 * Validate JWT
	 * @param authToken
	 * @return
	 */
	
	public boolean validateJwtToken(String authToken) {

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}
		catch (SignatureException  e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		}catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		} catch (Exception e) {
			log.error("General error {}", e.getMessage());
			throw e;
		}

		return false;
	}
	
	
}
