package br.com.zevolution.imdb.infrastructure.auditing;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.zevolution.imdb.config.security.AuthUser;
import br.com.zevolution.imdb.domain.entity.GeneralUser;
import br.com.zevolution.imdb.domain.repository.GeneralUserRepository;

public class CustomSpringSecurityAuditorAware implements AuditorAware<GeneralUser> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomSpringSecurityAuditorAware.class);
	
	private GeneralUserRepository generalUserRepository;
	
	public CustomSpringSecurityAuditorAware(GeneralUserRepository generalUserRepository) {
		this.generalUserRepository = generalUserRepository;
	} 

	@Override
	public Optional<GeneralUser> getCurrentAuditor() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	    	return Optional.empty();
	    }
	    
	    Object user = authentication.getPrincipal();
	    if (user instanceof AuthUser) {
	    	try {
		    	Long userId = AuthUser.class.cast(user).getId();
		    	return Optional.of(this.generalUserRepository.getOne(userId));
			} catch (EntityNotFoundException e) {
				LOGGER.error("User not found in CustomSpringSecurityAuditorAware.getCurrentAuditor()", e);
			}
	    } else {
	    	LOGGER.warn("Verify - instanceof not equals AuthUser in CustomSpringSecurityAuditorAware.getCurrentAuditor()");
	    }
	    
	    return Optional.empty();
	}
}
