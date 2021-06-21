package br.com.zevolution.imdb.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.zevolution.imdb.domain.dto.request.ActorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.ActorDTOResponse;
import br.com.zevolution.imdb.domain.exception.EntityNotFoundException;
import br.com.zevolution.imdb.domain.mapper.ActorMapper;
import br.com.zevolution.imdb.domain.repository.ActorRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ActorService {
	
	private static final String ACTOR_NOT_FOUND_MESSAGE = "Actor with id '%d' not found.";
	
	private ActorRepository actorRepository;
	private ActorMapper actorMapper;
	
	public ActorDTOResponse insert(ActorDTORequest input) {
		return this.actorMapper.map(this.actorRepository.save(this.actorMapper.map(input)));
	}
	
	@Transactional
	public ActorDTOResponse update(Long id, ActorDTORequest input) {
		return this.actorRepository.findById(id).map(entity -> {
			this.actorMapper.map(input, entity);
			return this.actorMapper.map(this.actorRepository.save(entity));
		}).orElseThrow(() -> new EntityNotFoundException(String.format(ACTOR_NOT_FOUND_MESSAGE, id)));
	}

	public ActorDTOResponse get(Long id) {
		return this.actorRepository.findByIdActive(id).map(this.actorMapper::map)
				.orElseThrow(() -> new EntityNotFoundException(String.format(ACTOR_NOT_FOUND_MESSAGE, id)));
	}

}
