package br.com.zevolution.imdb.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import br.com.zevolution.imdb.domain.dto.request.MovieDTORequest;
import br.com.zevolution.imdb.domain.dto.response.MovieDTOResponse;
import br.com.zevolution.imdb.domain.dto.response.MovieResumeDTOResponse;
import br.com.zevolution.imdb.domain.entity.Movie;
import br.com.zevolution.imdb.domain.entity.MovieVotes;
import br.com.zevolution.imdb.domain.exception.EntityNotFoundException;
import br.com.zevolution.imdb.domain.mapper.MovieMapper;
import br.com.zevolution.imdb.domain.mapper.MovieResumeMapper;
import br.com.zevolution.imdb.domain.repository.MovieRepository;
import br.com.zevolution.imdb.domain.repository.MovieVotesRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MovieService {
	
	private static final String MOVIE_NOT_FOUND_MESSAGE = "Movie with id '%d' not found.";
	
	private MovieRepository movieRepository;
	private MovieVotesRepository movieVotesRepository;
	private MovieMapper movieMapper;
	private MovieResumeMapper movieResumeMapper;
	
	public MovieDTOResponse insert(MovieDTORequest input) {
		return this.movieMapper.map(this.movieRepository.save(this.movieMapper.map(input)));
	}
	
	public void vote(Long id, Integer score) {
		this.movieRepository.findById(id).map(movie -> {
			MovieVotes movieVotes = new MovieVotes();
			
			movie.vote(score);
			movieVotes.setMovie(movie);
			movieVotes.setScore(score);
			
			this.movieRepository.save(movie);
			this.movieVotesRepository.save(movieVotes);
			
			return movie;
		}).orElseThrow(() -> new EntityNotFoundException(String.format(MOVIE_NOT_FOUND_MESSAGE, id)));
	}
	
	public MovieDTOResponse get(Long id) {
		return this.movieRepository.findByIdActive(id).map(this.movieMapper::map)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MOVIE_NOT_FOUND_MESSAGE, id)));
	}
	
	public Page<MovieResumeDTOResponse> getMoviesByFilters(Long directorId, String name, String genre,
			List<Long> actorsId, Pageable pageOption) {
		Page<Movie> paginateddMovies = this.movieRepository.findMoviesByFilters(directorId, name, genre, actorsId, pageOption);
		
		List<MovieResumeDTOResponse> moviesDTO = paginateddMovies.getContent().stream()
				.map(this.movieResumeMapper::map)
				.collect(Collectors.toList());
		
		return PageableExecutionUtils.getPage(moviesDTO, pageOption, () -> paginateddMovies.getTotalElements());
	}

}
