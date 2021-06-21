package br.com.zevolution.imdb.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.zevolution.imdb.domain.entity.MovieVotes;

@Repository
public interface MovieVotesRepository extends BaseRepository<MovieVotes, Long> {

}
