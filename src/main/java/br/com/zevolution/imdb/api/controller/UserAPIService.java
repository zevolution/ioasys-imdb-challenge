package br.com.zevolution.imdb.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zevolution.imdb.domain.dto.request.UserInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.UserUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.UserDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", tags = "User", description = "User REST Endpoints")
@RequestMapping(value = "/users")
@Validated
public interface UserAPIService {
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insert user", nickname = "insert")
	default ResponseEntity<UserDTOResponse> insert(
			@ApiParam(value = "User data") @Valid @RequestBody UserInsertDTORequest input) throws Exception {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update user", nickname = "update")
	default ResponseEntity<UserDTOResponse> update(
			@ApiParam @Valid @PathVariable Long id,
			@ApiParam(value = "User data") @Valid @RequestBody UserUpdateDTORequest input) throws Exception {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get user", nickname = "get")
	default ResponseEntity<UserDTOResponse> get(
			@ApiParam @Valid @PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete user", nickname = "delete")
	default ResponseEntity<UserDTOResponse> delete(
			@ApiParam @Valid @PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/all/paginated", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all users with pagination", nickname = "getAllPaginated")
	default ResponseEntity<Page<UserDTOResponse>> getAllPaginated(
			@ApiParam(required = true, defaultValue = "0") @Valid @RequestParam(required = true, defaultValue = "0") int pageNumber,
			@ApiParam(required = true, defaultValue = "10") @Valid @RequestParam(required = true, defaultValue = "10") int pageSize) throws Exception {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}