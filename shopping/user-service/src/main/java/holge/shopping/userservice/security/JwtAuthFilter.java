package holge.shopping.userservice.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import commons.dto.ApiResponse;
import commons.jwt.JwtUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    private JwtUtils jwtUtils;
    private RestTemplate restTemplate;
    private Gson gson;

    public JwtAuthFilter(JwtUtils jwtUtils, RestTemplate restTemplate, Gson gson) {
        this.jwtUtils = jwtUtils;
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = getAuthorization(request);
        
        if (jwt != null) {
        	// Call login service to valid the token
        	MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        	map.add("jwt", jwt);
        	
        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        	HttpEntity<MultiValueMap<String, String>> requestToLogin = new HttpEntity<MultiValueMap<String, String>>(map, headers);

            ResponseEntity<String> loginResponse = restTemplate.postForEntity("http://login-service/api/security/valid", requestToLogin, String.class);

            ApiResponse apiResponse = gson.fromJson(loginResponse.getBody(), ApiResponse.class);    
            boolean isValidToken = (Boolean) apiResponse.getData();
            
            if (isValidToken) {
                
                Authentication auth = jwtUtils.getAuthentication(jwt);

                if (auth != null) {
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(auth);
                    SecurityContextHolder.setContext(context);
                }
                
            } else {
                logger.warn("Invalid JWT");
            }
        }
        filterChain.doFilter(request, response);
    }

    public String getAuthorization(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth)) {
        	// Remove B|e|a|r|e|r| |
            return headerAuth.substring(7);
        }
        return null;
    }
}
