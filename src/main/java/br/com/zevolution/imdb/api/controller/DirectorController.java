package br.com.zevolution.imdb.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.zevolution.imdb.api.RestAPI;
import br.com.zevolution.imdb.domain.dto.request.DirectorDTORequest;
import br.com.zevolution.imdb.domain.dto.response.DirectorDTOResponse;
import br.com.zevolution.imdb.domain.service.DirectorService;
import lombok.AllArgsConstructor;

@RestAPI
@AllArgsConstructor
public class DirectorController implements DirectorAPIService {
	
	private DirectorService directorService;
	
	@Override
	public ResponseEntity<DirectorDTOResponse> insert(DirectorDTORequest input) {
		return new ResponseEntity<>(this.directorService.insert(input), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<DirectorDTOResponse> update(Long id, DirectorDTORequest input) {
		return ResponseEntity.ok(this.directorService.update(id, input));
	}
	
	@Override
	public ResponseEntity<DirectorDTOResponse> get(Long id) {
		return ResponseEntity.ok(this.directorService.get(id));
	}

}
