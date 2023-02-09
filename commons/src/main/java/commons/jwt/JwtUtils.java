package commons.jwt;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import commons.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Header;

import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.slf4j.Logger;

/**
 * Class to manage all JWT operations
 * @author Holge
 *
 */
@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Autowired
	Gson gson;

	public JwtUtils() {}
	

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

						
		// Return a authentication with user and authorities
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
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
        Jwt<Header,Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);

        // Get userDTO from JWT payload
        String userString = untrusted.getBody().getSubject(); 

        // Parse user jwt data json to UserDTO
        UserDTO userDTO = gson.fromJson(userString, UserDTO.class);
        return userDTO;
    }
	
	
}
