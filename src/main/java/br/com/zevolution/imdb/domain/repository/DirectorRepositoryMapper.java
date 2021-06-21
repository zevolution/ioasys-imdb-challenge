package br.com.zevolution.imdb.domain.repository;

import br.com.zevolution.imdb.domain.entity.Director;

public interface DirectorRepositoryMapper {
	
	Director getById(Long ID);

}
