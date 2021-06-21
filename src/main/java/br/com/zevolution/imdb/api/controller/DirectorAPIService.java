package br.com.zevolution.imdb.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zevolution.imdb.domain.dto.request.DirectorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.DirectorDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", tags = "Director", description = "Director REST Endpoints")
@RequestMapping(value = "/directors")
@Validated
public interface DirectorAPIService {
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insert director", nickname = "insert")
	default ResponseEntity<DirectorDTOResponse> insert(
			@ApiParam(value = "Director data") @Valid @RequestBody DirectorDTORequest input) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update director", nickname = "update")
	default ResponseEntity<DirectorDTOResponse> update(
			@ApiParam @Valid @PathVariable Long id,
			@ApiParam(value = "Director data") @Valid @RequestBody DirectorDTORequest input) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get director", nickname = "get")
	default ResponseEntity<DirectorDTOResponse> get(
			@ApiParam @Valid @PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}