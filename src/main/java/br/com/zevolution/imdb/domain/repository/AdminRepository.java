package br.com.zevolution.imdb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zevolution.imdb.domain.entity.Administrator;

@Repository
public interface AdminRepository extends BaseRepository<Administrator, Long>, JpaSpecificationExecutor<Administrator> {
	
	@Query("FROM User a " 
		 + "WHERE LOWER(a.login) = ?1 "
		 + "  AND a.active = 1 ")
	Optional<Administrator> findByLoginActive(String login);

}
