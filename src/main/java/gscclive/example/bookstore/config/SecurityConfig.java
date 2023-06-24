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
		String authorUrl = "/author/**";

		http.authorizeHttpRequests(authz -> authz
				.requestMatchers(HttpMethod.GET, bookUrl)
				.hasAnyRole(USER_ROLE, ADMIN_ROLE)
				.requestMatchers(HttpMethod.PATCH, bookUrl)
				.hasAnyRole(USER_ROLE)
				.requestMatchers(HttpMethod.POST, bookUrl)
				.hasAnyRole(USER_ROLE)
				.requestMatchers(HttpMethod.DELETE, bookUrl)
				.hasRole(ADMIN_ROLE)
				.requestMatchers(HttpMethod.GET, authorUrl)
				.hasAnyRole(USER_ROLE, ADMIN_ROLE)
				.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.formLogin(form -> form.disable());
		return http.build();
	}

}
