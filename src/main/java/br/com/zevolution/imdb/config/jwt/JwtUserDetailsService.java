package br.com.zevolution.imdb.config.jwt;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zevolution.imdb.config.security.AuthUser;
import br.com.zevolution.imdb.domain.entity.SystemUser;
import br.com.zevolution.imdb.domain.entity.UserProfileLevel;
import br.com.zevolution.imdb.domain.repository.GeneralUserRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private GeneralUserRepository generalUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUser user = this.generalUserRepository.findByLoginActive(StringUtils.lowerCase(username))
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
		return new AuthUser(user, this.getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(SystemUser user) {
		if (UserProfileLevel.ADMINISTRATOR.equals(user.getProfileLevel())) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

}