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

import br.com.zevolution.imdb.domain.dto.request.ActorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.ActorDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", tags = "Actor", description = "Actor REST Endpoints")
@RequestMapping(value = "/actors")
@Validated
public interface ActorAPIService {
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insert actor", nickname = "insert")
	default ResponseEntity<ActorDTOResponse> insert(
			@ApiParam(value = "Actor data") @Valid @RequestBody ActorDTORequest input) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update actor", nickname = "update")
	default ResponseEntity<ActorDTOResponse> update(
			@ApiParam @Valid @PathVariable Long id,
			@ApiParam(value = "Actor data") @Valid @RequestBody ActorDTORequest input) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get actor", nickname = "get")
	default ResponseEntity<ActorDTOResponse> get(
			@ApiParam @Valid @PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}