package br.com.zevolution.imdb.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.zevolution.imdb.api.RestAPI;
import br.com.zevolution.imdb.domain.dto.request.ActorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.ActorDTOResponse;
import br.com.zevolution.imdb.domain.service.ActorService;
import lombok.AllArgsConstructor;

@RestAPI
@AllArgsConstructor
public class ActorController implements ActorAPIService {
	
	private ActorService actorService;
	
	@Override
	public ResponseEntity<ActorDTOResponse> insert(ActorDTORequest input) {
		return new ResponseEntity<>(this.actorService.insert(input), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<ActorDTOResponse> update(Long id, ActorDTORequest input) {
		ActorDTOResponse update = this.actorService.update(id, input);
		return ResponseEntity.ok(update);
	}
	
	@Override
	public ResponseEntity<ActorDTOResponse> get(Long id) {
		return ResponseEntity.ok(this.actorService.get(id));
	}

}
