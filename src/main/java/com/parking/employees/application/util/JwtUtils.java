package com.parking.employees.application.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.experimental.UtilityClass;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;

@UtilityClass
public class JwtUtils {

	private final Long TOKEN_EXPIRATION_TIME = 3600000L;
	
	@SneakyThrows
	public String createToken(String username, String rol, String jwtSecret) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("username", username);
		payload.put("rol", rol);
		return Jwts.builder()
				.setClaims(payload)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.setHeaderParam("typ", "JWT")
				.signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes("UTF-8")).compact();
	}
	
}
