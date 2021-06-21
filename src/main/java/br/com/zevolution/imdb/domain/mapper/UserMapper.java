package br.com.zevolution.imdb.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.zevolution.imdb.domain.dto.request.UserInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.UserUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.UserDTOResponse;
import br.com.zevolution.imdb.domain.entity.User;

@Mapper
public interface UserMapper {
	
	User map(UserInsertDTORequest source);
	
	UserDTOResponse map(User source);
	
	void map(UserUpdateDTORequest source, @MappingTarget User target);

}
