package br.com.zevolution.imdb.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.zevolution.imdb.domain.dto.request.AdminInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.AdminUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.AdminDTOResponse;
import br.com.zevolution.imdb.domain.exception.EntityNotFoundException;
import br.com.zevolution.imdb.domain.mapper.AdminMapper;
import br.com.zevolution.imdb.domain.repository.AdminRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AdminService {
	
	private static final String ADMIN_NOT_FOUND_MESSAGE = "Admin with id '%d' not found.";
	
	private AdminRepository adminRepository;
	private AdminMapper adminMapper;
	
	public AdminDTOResponse insert(AdminInsertDTORequest input) {
		return this.adminMapper.map(this.adminRepository.save(this.adminMapper.map(input)));
	}
	
	public AdminDTOResponse update(Long id, AdminUpdateDTORequest input) {
		return this.adminRepository.findById(id).map(entity -> {
			this.adminMapper.map(input, entity);
			this.adminRepository.save(entity);
			return this.adminMapper.map(entity);
		}).orElseThrow(() -> new EntityNotFoundException(String.format(ADMIN_NOT_FOUND_MESSAGE, id)));
	}
	
	public AdminDTOResponse get(Long id) {
		return this.adminRepository.findByIdActive(id).map(this.adminMapper::map)
				.orElseThrow(() -> new EntityNotFoundException(String.format(ADMIN_NOT_FOUND_MESSAGE, id)));
	}
	
	public AdminDTOResponse softDelete(Long id) {
		return this.adminRepository.findById(id).map(entity -> {
			this.adminRepository.softDelete(entity);
			return this.adminMapper.map(entity);
		}).orElseThrow(() -> new EntityNotFoundException(String.format(ADMIN_NOT_FOUND_MESSAGE, id)));
	}

}
