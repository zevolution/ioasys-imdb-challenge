package br.com.zevolution.imdb.api.controller;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.zevolution.imdb.api.RestAPI;
import br.com.zevolution.imdb.config.jwt.JwtTokenService;
import br.com.zevolution.imdb.config.jwt.UserAuthenticateRequest;
import br.com.zevolution.imdb.config.jwt.UserAuthenticateResponse;
import lombok.AllArgsConstructor;

@RestAPI
@AllArgsConstructor
public class AuthController implements AuthAPIService {
	
	private AuthenticationManager authenticationManager;
	private JwtTokenService jwtTokenService;
	private UserDetailsService userDetailsService;

	public ResponseEntity<?> authenticateUser(UserAuthenticateRequest input) throws Exception {
		this.authenticate(input.getUserLogin(), input.getPassword());

		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(input.getUserLogin());

		final String token = this.jwtTokenService.generateToken(userDetails);

		return ResponseEntity.ok(new UserAuthenticateResponse(token, this.jwtTokenService.getExpirationDateFromToken(token)));
	}

	private void authenticate(String login, String password) throws Exception {
		Objects.requireNonNull(login);
		Objects.requireNonNull(password);

		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
		} catch (DisabledException e) {
			throw new RuntimeException(e);
		} catch (BadCredentialsException e) {
			throw new RuntimeException(e);
		}
	}

}
