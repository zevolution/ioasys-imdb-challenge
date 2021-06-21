package br.com.zevolution.imdb.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.zevolution.imdb.domain.entity.Movie;

@Repository
public interface MovieRepository extends BaseRepository<Movie, Long>, MovieRepositoryCustom {

}
