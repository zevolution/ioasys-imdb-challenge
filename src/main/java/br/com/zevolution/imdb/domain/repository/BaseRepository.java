package br.com.zevolution.imdb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.zevolution.imdb.domain.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity, ID> extends JpaRepository<Entity, ID> {
	
	default void softDelete(Entity entity) {
		entity.setActive(false);
		this.save(entity);
	}
	
	@Query("FROM #{#entityName} a WHERE a.id = ?1 AND a.active = 1")
	Optional<Entity> findByIdActive(ID id);
}
