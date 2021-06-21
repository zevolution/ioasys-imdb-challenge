package br.com.zevolution.imdb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zevolution.imdb.config.jwt.JwtAuthenticationEntryPoint;
import br.com.zevolution.imdb.config.jwt.JwtRequestFilter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String[] PUBLIC_ENDPOINTS = { "/auth/authenticate", "/v2/api-docs", 
			"/swagger-resources/**", "/configuration/ui", "/configuration/security", 
			"/swagger-ui.html", "/webjars/**", "/h2-console/**"};
	
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private UserDetailsService jwtUserDetailsService;
	private JwtRequestFilter jwtRequestFilter;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.jwtUserDetailsService).passwordEncoder(this.passwordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable().headers().frameOptions().disable().and()
			.authorizeRequests().antMatchers(PUBLIC_ENDPOINTS).permitAll()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
