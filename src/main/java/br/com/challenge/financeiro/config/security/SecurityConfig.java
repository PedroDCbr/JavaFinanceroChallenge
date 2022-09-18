package br.com.challenge.financeiro.config.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import br.com.challenge.financeiro.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
@Profile(value = {"prod", "test"})
public class SecurityConfig {
	
	@Autowired
	public AutenticacaoService autenticacaoService;
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/**/api-docs").permitAll()
				.antMatchers("**/favicon.ico", "/css/**", "/images/**", "/js/**", "/webjars/**").permitAll()
              .anyRequest().authenticated().and()                
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);			
		
		return http.build();
	    
	  }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		/* 123456 : $2a$10$N9bnr8/XJ/t6yfBbqD79VeeYzIwIoJjpAc1NWtoe921hRnQRVyP9q */
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(passwordEncoder());
	}
	
}
