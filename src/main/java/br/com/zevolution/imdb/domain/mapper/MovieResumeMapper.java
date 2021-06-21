package br.com.zevolution.imdb.domain.mapper;

import org.mapstruct.Mapper;

import br.com.zevolution.imdb.domain.dto.response.MovieResumeDTOResponse;
import br.com.zevolution.imdb.domain.entity.Movie;

@Mapper
public interface MovieResumeMapper {
	
	MovieResumeDTOResponse map(Movie source);

}
