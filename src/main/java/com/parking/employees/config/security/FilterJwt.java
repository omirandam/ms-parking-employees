package com.parking.employees.config.security;


import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.parking.employees.application.util.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class FilterJwt extends OncePerRequestFilter  {

	private static final String MSG_NOTFOUND_HEADER_AUTHORIZATION = "No se encontr\u00F3 header Authorization";
	private static final String MSG_UNAUTHORIZED = "No autorizado";
	private static final String MSG_INVALID_TOKEN = "El token no es v\u00E1lido";
	private static final String MSG_EXPIRED_TOKEN = "El token expir\u00F3";
	private static final String PAYLOAD_USERNAME = "username";
	private static final String PAYLOAD_ROLES="rol";
	private String secret;
	private String[] AUTH_WHITELIST_FILTER;
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";	
	public static final String DISPLAY_NAME = "displayName";
	public static final String USERNAME = "username";
	 
	public FilterJwt(String secret,String[] AUTH_WHITELIST) {
			 setSecret(secret);
			 setAUTH_WHITELIST_FILTER(AUTH_WHITELIST);
	}
		
    public FilterJwt() {}
		  
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		System.out.println("Filter jwt interceptor");
				try {
					SecurityContextHolder.clearContext();
					if (existeJWTToken(request)) {
						Claims claims = validateToken(request);						
						setUpSpringAuthentication(claims);						
					} else {		
						Utils.handleException(request, response,MSG_NOTFOUND_HEADER_AUTHORIZATION,HttpStatus.FORBIDDEN.value());
					}
					chain.doFilter(request, response);
				} 
				catch (IOException e) {
					Utils.handleException(request, response,MSG_UNAUTHORIZED,HttpStatus.FORBIDDEN.value());      
				}
				catch(ExpiredJwtException e) {
					Utils.handleException(request, response,MSG_EXPIRED_TOKEN,HttpStatus.UNAUTHORIZED.value());  
				 }
				catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
					Utils.handleException(request, response,MSG_INVALID_TOKEN,HttpStatus.FORBIDDEN.value());      
				}
			}	

			private Claims validateToken(HttpServletRequest request) {
				String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY).replace(TOKEN_BEARER_PREFIX, "");
				return Jwts.parser().setSigningKey(getSecret().getBytes()).parseClaimsJws(jwtToken).getBody();
			}

			private void setUpSpringAuthentication(Claims claims) {		
				Object rol = claims.get(PAYLOAD_ROLES);
				System.out.println(rol.toString());
				UsernamePasswordAuthenticationToken auth = null;
				if(rol != null) {
					 auth = new UsernamePasswordAuthenticationToken(claims.getSubject(),null,Arrays.asList(new SimpleGrantedAuthority("ROLE_" + rol.toString())));
				}
				
				Object username=claims.get(PAYLOAD_USERNAME);
				if(username!=null) {
					auth.setDetails(username.toString());
				}
				SecurityContextHolder.getContext().setAuthentication(auth);

			}

			private boolean existeJWTToken(HttpServletRequest request) {
				String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
				if (authenticationHeader == null || !authenticationHeader.startsWith(TOKEN_BEARER_PREFIX))
					return false;
				return true;
			}

			@Override
			protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
			
				return Utils.matcher(AUTH_WHITELIST_FILTER, request.getRequestURI());
			}
			
			
}
