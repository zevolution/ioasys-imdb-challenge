package br.com.zevolution.imdb.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zevolution.imdb.config.jwt.UserAuthenticateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", tags = "Authentication", description = "Authentication REST Endpoints")
@RequestMapping(value = "/auth")
@Validated
public interface AuthAPIService {
	
	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Authenticate user", nickname = "authenticateUser")
	default ResponseEntity<?> authenticateUser(
			@ApiParam(value = "Login data") @Valid @RequestBody UserAuthenticateRequest input) throws Exception {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}