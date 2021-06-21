package br.com.zevolution.imdb.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zevolution.imdb.domain.dto.request.MovieDTORequest;
import br.com.zevolution.imdb.domain.dto.response.MovieDTOResponse;
import br.com.zevolution.imdb.domain.dto.response.MovieResumeDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", tags = "Movie", description = "Movie REST Endpoints")
@RequestMapping(value = "/movies")
@Validated
public interface MovieAPIService {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insert movie", nickname = "insert")
	default ResponseEntity<MovieDTOResponse> insert(
			@ApiParam(value = "Movie data") @Valid @RequestBody MovieDTORequest input) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get movie", nickname = "get")
	default ResponseEntity<MovieDTOResponse> get(
			@ApiParam @Valid @PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(value = "/{id}/vote/{score}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Vote in movie", nickname = "vote")
	default ResponseEntity<Void> vote(
			@ApiParam @Valid @PathVariable Long id,
			@ApiParam @Valid @PathVariable @Min(0) @Max(10) Integer score) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@GetMapping(value = "/filters/dynamic/paginated", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get movies by dynamic filter", nickname = "getByFilters")
	default ResponseEntity<Page<MovieResumeDTOResponse>> getByFilters(
			@ApiParam(required = false) @Valid @RequestParam(required = false) Long directorId,
			@ApiParam(required = false) @Valid @RequestParam(required = false) String name,
			@ApiParam(required = false) @Valid @RequestParam(required = false) String genre,
			@ApiParam(required = false) @Valid @RequestParam(required = false) List<Long> actorsId,
			@ApiParam(required = true, defaultValue = "0") @Valid @RequestParam(required = true, defaultValue = "0") int pageNumber,
			@ApiParam(required = true, defaultValue = "10") @Valid @RequestParam(required = true, defaultValue = "10") int pageSize) throws Exception {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}