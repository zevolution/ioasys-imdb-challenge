package br.com.zevolution.imdb.domain.repository;

import java.util.List;

import br.com.zevolution.imdb.domain.entity.Actor;

public interface ActorRepositoryMapper {
	
	List<Actor> findAllById(Iterable<Long> ID);

}
