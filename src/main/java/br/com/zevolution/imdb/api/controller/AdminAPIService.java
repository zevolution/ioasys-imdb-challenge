package br.com.zevolution.imdb.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zevolution.imdb.domain.dto.request.AdminInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.AdminUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.AdminDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", tags = "Administrators", description = "Administrators REST Endpoints")
@RequestMapping(value = "/admin")
@Validated
public interface AdminAPIService {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insert admin", nickname = "insert")
	default ResponseEntity<AdminDTOResponse> insert(
			@ApiParam(value = "Admin data") @Valid @RequestBody AdminInsertDTORequest input) throws Exception {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update admin", nickname = "update")
	default ResponseEntity<AdminDTOResponse> update(
			@ApiParam @Valid @PathVariable Long id,
			@ApiParam(value = "Admin data") @Valid @RequestBody AdminUpdateDTORequest input) throws Exception {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get admin", nickname = "get")
	default ResponseEntity<AdminDTOResponse> get(
			@ApiParam @Valid @PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete admin", nickname = "delete")
	default ResponseEntity<AdminDTOResponse> delete(
			@ApiParam @Valid @PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
}