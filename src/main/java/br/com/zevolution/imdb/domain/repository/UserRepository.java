package br.com.zevolution.imdb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zevolution.imdb.domain.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	@Query("FROM User a " 
		 + "WHERE LOWER(a.login) = ?1 "
		 + "AND a.active = 1")
	Optional<User> findByLoginActive(String login);

}
