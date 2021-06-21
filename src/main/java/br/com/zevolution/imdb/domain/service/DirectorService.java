package br.com.zevolution.imdb.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.zevolution.imdb.domain.dto.request.DirectorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.DirectorDTOResponse;
import br.com.zevolution.imdb.domain.exception.EntityNotFoundException;
import br.com.zevolution.imdb.domain.mapper.DirectorMapper;
import br.com.zevolution.imdb.domain.repository.DirectorRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DirectorService {
	
	private static final String DIRECTOR_NOT_FOUND_MESSAGE = "Director with id '%d' not found.";
	
	private DirectorRepository directorRepository;
	private DirectorMapper directorMapper;
	
	public DirectorDTOResponse insert(DirectorDTORequest input) {
		return this.directorMapper.map(this.directorRepository.save(this.directorMapper.map(input)));
	}
	
	public DirectorDTOResponse update(Long id, DirectorDTORequest input) {
		return this.directorRepository.findById(id).map(entity -> {
			this.directorMapper.map(input, entity);
			this.directorRepository.save(entity);
			return this.directorMapper.map(entity);
		}).orElseThrow(() -> new EntityNotFoundException(String.format(DIRECTOR_NOT_FOUND_MESSAGE, id)));
	}

	public DirectorDTOResponse get(Long id) {
		return this.directorRepository.findByIdActive(id).map(this.directorMapper::map)
				.orElseThrow(() -> new EntityNotFoundException(String.format(DIRECTOR_NOT_FOUND_MESSAGE, id)));
	}

}
