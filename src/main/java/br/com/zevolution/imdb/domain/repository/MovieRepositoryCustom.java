package br.com.zevolution.imdb.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.zevolution.imdb.domain.entity.Movie;

public interface MovieRepositoryCustom {
	
	Page<Movie> findMoviesByFilters(Long directorId, String name, String genre,
			List<Long> actorsId, Pageable pageOption);

}
