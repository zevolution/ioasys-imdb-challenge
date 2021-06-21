package br.com.zevolution.imdb.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.zevolution.imdb.domain.dto.request.MovieDTORequest;
import br.com.zevolution.imdb.domain.dto.response.MovieDTOResponse;
import br.com.zevolution.imdb.domain.entity.Movie;
import br.com.zevolution.imdb.domain.repository.ActorRepositoryMapper;
import br.com.zevolution.imdb.domain.repository.DirectorRepositoryMapper;

@Mapper(uses = { ActorRepositoryMapper.class, DirectorRepositoryMapper.class })
public interface MovieMapper {
	
	MovieDTOResponse map(Movie source);
	
	Movie map(MovieDTORequest source);
	
	List<MovieDTOResponse> map(List<Movie> source);
	
}
