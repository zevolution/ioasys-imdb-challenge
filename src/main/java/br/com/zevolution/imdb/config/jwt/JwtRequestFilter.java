package br.com.zevolution.imdb.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RegExUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	private JwtUserDetailsService jwtUserDetailsService;
	private JwtTokenService jwtTokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		try {
			final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
	
			if (requestTokenHeader != null && !requestTokenHeader.startsWith(BEARER_PREFIX)) {
				throw new MalformedJwtException("JWT Token does not begin with prefix.");
			}
			
			if (requestTokenHeader != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				String jwtToken = RegExUtils.replaceAll(requestTokenHeader, BEARER_PREFIX, "");
				String username = this.jwtTokenService.getUsernameFromToken(jwtToken);
				
				this.setupAuthentication(request, username, jwtToken);
			}
			
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			LOGGER.warn("JwtRequestFilter", e);
			throw new JwtException("Unable to validate the access token.", e);
		}
		
	}

	private void setupAuthentication(HttpServletRequest request, String username, String jwtToken) {
		UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

		if (this.jwtTokenService.validateToken(jwtToken, userDetails)) {

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, userDetails.getAuthorities(), userDetails.getAuthorities());
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}
	
}
