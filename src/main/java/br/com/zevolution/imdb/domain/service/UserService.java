package br.com.zevolution.imdb.domain.service;

import static br.com.zevolution.imdb.domain.entity.UserProfileLevel.USER;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.zevolution.imdb.domain.dto.request.UserInsertDTORequest;
import br.com.zevolution.imdb.domain.dto.request.UserUpdateDTORequest;
import br.com.zevolution.imdb.domain.dto.response.UserDTOResponse;
import br.com.zevolution.imdb.domain.entity.User;
import br.com.zevolution.imdb.domain.entity.UserProfileLevel;
import br.com.zevolution.imdb.domain.exception.EntityNotFoundException;
import br.com.zevolution.imdb.domain.mapper.UserMapper;
import br.com.zevolution.imdb.domain.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
	
	private static final String USER_NOT_FOUND_MESSAGE = "User with id '%d' not found.";
	
	private UserRepository userRepository;
	private UserMapper userMapper;
	
	public UserDTOResponse insert(UserInsertDTORequest input) {
		return this.userMapper.map(this.userRepository.save(this.userMapper.map(input)));
	}
	
	public UserDTOResponse update(Long id, UserUpdateDTORequest input) {
		return this.userRepository.findById(id).map(entity -> {
			this.userMapper.map(input, entity);
			this.userRepository.save(entity);
			return this.userMapper.map(entity);
		}).orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
	}
	
	public UserDTOResponse get(Long id) {
		return this.userRepository.findByIdActive(id).map(this.userMapper::map)
				.orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
	}
	
	public UserDTOResponse softDelete(Long id) {
		return this.userRepository.findById(id).map(entity -> {
			this.userRepository.softDelete(entity);
			return this.userMapper.map(entity);
		}).orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
	}
	
	public Page<User> getUsersFromUserProfileAndActive(Pageable pageOptions) {
		return this.userRepository.findAll(this.byUserProfileAndActiveSortedByName(USER), pageOptions);
	}
	
	private Specification<User> byUserProfileAndActiveSortedByName(UserProfileLevel profile) {
		return (root, query, builder) -> {
			query.orderBy(builder.asc(root.get("name")));
			return builder.and(builder.equal(root.get("profileLevel"), profile), 
					builder.equal(root.get("active"), true));
		};
	}

}
