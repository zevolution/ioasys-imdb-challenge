package br.com.zevolution.imdb.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.zevolution.imdb.api.RestAPI;
import br.com.zevolution.imdb.domain.dto.request.UserInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.UserUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.UserDTOResponse;
import br.com.zevolution.imdb.domain.entity.User;
import br.com.zevolution.imdb.domain.mapper.UserMapper;
import br.com.zevolution.imdb.domain.service.UserService;
import lombok.AllArgsConstructor;

@RestAPI
@AllArgsConstructor
public class UserController implements UserAPIService {
	
	private UserService userService;
	private UserMapper userMapper;
	
	@Override
	public ResponseEntity<UserDTOResponse> insert(UserInsertDTORequest input) throws Exception {
		return new ResponseEntity<>(this.userService.insert(input), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<UserDTOResponse> update(Long id, UserUpdateDTORequest input) throws Exception {
		return ResponseEntity.ok(this.userService.update(id, input));
	}
	
	@Override
	public ResponseEntity<UserDTOResponse> get(Long id) {
		return ResponseEntity.ok(this.userService.get(id));
	}
	
	@Override
	public ResponseEntity<UserDTOResponse> delete(Long id) {
		return ResponseEntity.ok(this.userService.softDelete(id));
	}
	
	@Override
	public ResponseEntity<Page<UserDTOResponse>> getAllPaginated(int pageNumber, int pageSize) throws Exception {
		Pageable pageOptions = PageRequest.of(pageNumber, pageSize);
		
		Page<User> paginatedUsers = this.userService.getUsersFromUserProfileAndActive(pageOptions);
		
		List<UserDTOResponse> usersDTO = paginatedUsers.getContent().stream()
				.map(this.userMapper::map).collect(Collectors.toList());
		
		return ResponseEntity.ok(PageableExecutionUtils.getPage(usersDTO, pageOptions, () -> paginatedUsers.getTotalElements()));
	}

}
