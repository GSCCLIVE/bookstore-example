package gscclive.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration beans.
 * 
 * @author GCLIVE 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		UserDetails admin = User.builder()
				.username("admin")
				.password(encoder.encode("password"))
				.roles(ADMIN_ROLE)
				.build();
		UserDetails user = User.builder()
				.username("user")
				.password(encoder.encode("password"))
				.roles(USER_ROLE)
				.build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		String bookUrl = "/book/**";
		String actuator = "/actuator/**";

		http.authorizeHttpRequests(authz -> authz
				.requestMatchers(HttpMethod.DELETE, bookUrl)
				.hasRole(USER_ROLE)
				.requestMatchers(actuator)
				.permitAll()
				.requestMatchers(bookUrl)
				.permitAll()
				.anyRequest().denyAll())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.formLogin(form -> form.disable());
		return http.build();
	}

}
