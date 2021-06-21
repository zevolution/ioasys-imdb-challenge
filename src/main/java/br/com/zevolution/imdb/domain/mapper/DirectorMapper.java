package br.com.zevolution.imdb.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.zevolution.imdb.domain.dto.request.DirectorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.DirectorDTOResponse;
import br.com.zevolution.imdb.domain.entity.Director;

@Mapper
public interface DirectorMapper {
	
	DirectorDTOResponse map(Director source);
	
	Director map(DirectorDTORequest source);
	
	void map(DirectorDTORequest source, @MappingTarget Director target);

}
