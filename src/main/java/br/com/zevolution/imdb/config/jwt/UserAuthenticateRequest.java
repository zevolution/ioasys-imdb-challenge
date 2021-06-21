package br.com.zevolution.imdb.config.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticateRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String userLogin;
	private String password;
	
}