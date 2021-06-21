package br.com.zevolution.imdb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zevolution.imdb.domain.entity.GeneralUser;

@Repository
public interface GeneralUserRepository extends BaseRepository<GeneralUser, Long> {
	
	@Query("FROM GeneralUser a " 
		 + "WHERE LOWER(a.login) = ?1 "
		 + "AND a.active = 1")
	Optional<GeneralUser> findByLoginActive(String login);

}
