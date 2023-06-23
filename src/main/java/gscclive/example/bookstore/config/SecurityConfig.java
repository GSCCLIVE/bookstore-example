package gscclive.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = org.springframework.security.core.userdetails.User.builder()
				.username("user")
				.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
				.roles(ADMIN_ROLE)
				.build();

		UserDetails user = org.springframework.security.core.userdetails.User.builder()
				.username("admin")
				.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
				.roles(ADMIN_ROLE)
				.build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		String bookUrl = "/book";
		String authorUrl = "/author/**";

		http.authorizeHttpRequests((authz) -> authz
				.requestMatchers(HttpMethod.GET, bookUrl)
				.hasAnyRole(USER_ROLE)
				.requestMatchers(HttpMethod.PATCH, bookUrl)
				.hasAnyRole(USER_ROLE)
				.requestMatchers(HttpMethod.POST, bookUrl)
				.hasAnyRole(USER_ROLE)
				.requestMatchers(HttpMethod.DELETE, bookUrl)
				.hasAuthority(ADMIN_ROLE)
				.requestMatchers(HttpMethod.GET, authorUrl)
				.hasAnyRole(USER_ROLE)
				.requestMatchers("/login")
				.permitAll()
				.requestMatchers("/**")
				.authenticated()
				.anyRequest().denyAll())
				.httpBasic(Customizer.withDefaults())
				.csrf(csrf -> csrf.disable())
				.formLogin(Customizer.withDefaults());
		// .formLogin(form -> form.disable());
		return http.build();
	}

}
