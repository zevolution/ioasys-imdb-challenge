package br.com.zevolution.imdb.domain.listener;

import javax.persistence.PrePersist;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.zevolution.imdb.domain.entity.SystemUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserEncodePasswordListener {
	
	private PasswordEncoder passwordEncoder;
	
    @PrePersist
    public void prePersist(SystemUser systemUser)  {
    	systemUser.setPassword(this.passwordEncoder.encode(systemUser.getPassword()));
    }
	
}
