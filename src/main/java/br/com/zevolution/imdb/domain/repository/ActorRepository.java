package br.com.zevolution.imdb.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.zevolution.imdb.domain.entity.Actor;

@Repository
public interface ActorRepository extends BaseRepository<Actor, Long>, ActorRepositoryMapper {

}
