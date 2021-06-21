package br.com.zevolution.imdb.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.zevolution.imdb.api.RestAPI;
import br.com.zevolution.imdb.domain.dto.request.AdminInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.AdminUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.AdminDTOResponse;
import br.com.zevolution.imdb.domain.service.AdminService;
import lombok.AllArgsConstructor;

@RestAPI
@AllArgsConstructor
public class AdminController implements AdminAPIService {
	
	private AdminService adminService;
	
	@Override
	public ResponseEntity<AdminDTOResponse> insert(AdminInsertDTORequest input) throws Exception {
		return new ResponseEntity<>(this.adminService.insert(input), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<AdminDTOResponse> update(Long id, AdminUpdateDTORequest input) throws Exception {
		return ResponseEntity.ok(this.adminService.update(id, input));
	}
	
	@Override
	public ResponseEntity<AdminDTOResponse> get(Long id) {
		return ResponseEntity.ok(this.adminService.get(id));
	}
	
	@Override
	public ResponseEntity<AdminDTOResponse> delete(Long id) {
		return ResponseEntity.ok(this.adminService.softDelete(id));
	}

}
