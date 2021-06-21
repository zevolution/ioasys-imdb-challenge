package br.com.zevolution.imdb.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.zevolution.imdb.domain.entity.SystemUser;
import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 855746071490955944L;
	
	private Long id;

	public AuthUser(SystemUser user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getLogin(), user.getPassword(), user.isActive(), true, true, true, authorities);
		this.id = user.getId();
	}
	
}
