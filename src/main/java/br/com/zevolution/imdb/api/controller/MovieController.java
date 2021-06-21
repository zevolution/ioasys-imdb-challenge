package br.com.zevolution.imdb.api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.zevolution.imdb.api.RestAPI;
import br.com.zevolution.imdb.domain.dto.request.MovieDTORequest;
import br.com.zevolution.imdb.domain.dto.response.MovieDTOResponse;
import br.com.zevolution.imdb.domain.dto.response.MovieResumeDTOResponse;
import br.com.zevolution.imdb.domain.service.MovieService;
import lombok.AllArgsConstructor;

@RestAPI
@AllArgsConstructor
public class MovieController implements MovieAPIService {
	
	private MovieService movieService;
	
	@Override
	public ResponseEntity<MovieDTOResponse> insert(MovieDTORequest input) {
		return new ResponseEntity<>(this.movieService.insert(input), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<MovieDTOResponse> get(Long id) {
		return ResponseEntity.ok(this.movieService.get(id));
	}
	
	@Override
	public ResponseEntity<Void> vote(Long id, Integer score) {
		this.movieService.vote(id, score);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Page<MovieResumeDTOResponse>> getByFilters(Long directorId, String name, String genre,
			List<Long> actorsId, int pageNumber, int pageSize) throws Exception {
		return ResponseEntity.ok(this.movieService.getMoviesByFilters(directorId, name, genre, 
				actorsId, PageRequest.of(pageNumber, pageSize)));
	}
	
}
