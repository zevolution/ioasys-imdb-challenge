package br.com.zevolution.imdb.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.zevolution.imdb.domain.dto.request.AdminInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.AdminUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.AdminDTOResponse;
import br.com.zevolution.imdb.domain.entity.Administrator;

@Mapper
public interface AdminMapper {
	
	Administrator map(AdminInsertDTORequest source);
	
	AdminDTOResponse map(Administrator source);
	
	void map(AdminUpdateDTORequest source, @MappingTarget Administrator target);

}
