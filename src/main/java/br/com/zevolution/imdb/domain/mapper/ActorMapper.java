package br.com.zevolution.imdb.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.zevolution.imdb.domain.dto.request.ActorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.ActorDTOResponse;
import br.com.zevolution.imdb.domain.entity.Actor;

@Mapper
public interface ActorMapper {
	
	ActorDTOResponse map(Actor source);
	
	Actor map(ActorDTORequest source);
	
	void map(ActorDTORequest source, @MappingTarget Actor target);
	
}
