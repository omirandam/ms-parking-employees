package com.parking.employees.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Value("${jwt.secret}")
	private String key;
	
	private static final String[] AUTH_WHITELIST = {
			  "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/health",
            "/refresh",
            "/auth/login"
	    };
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.addFilterBefore(new FilterJwt(key,AUTH_WHITELIST), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(AUTH_WHITELIST).permitAll()
		//.antMatchers("/api/parameter/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.headers()	        
		.httpStrictTransportSecurity();
		http.cors();
	}

	/*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
	
}
