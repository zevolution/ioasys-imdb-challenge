package br.com.zevolution.imdb.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.zevolution.imdb.domain.entity.Director;

@Repository
public interface DirectorRepository extends BaseRepository<Director, Long>, DirectorRepositoryMapper {

}
