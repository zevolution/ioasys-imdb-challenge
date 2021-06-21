package br.com.zevolution.imdb.config.auditing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import br.com.zevolution.imdb.domain.entity.GeneralUser;
import br.com.zevolution.imdb.domain.repository.GeneralUserRepository;
import br.com.zevolution.imdb.infrastructure.auditing.CustomSpringSecurityAuditorAware;

@Configuration
public class CustomJpaAuditingConfig {

	private GeneralUserRepository generalUserRepository;
	
	@Autowired
	public CustomJpaAuditingConfig(GeneralUserRepository generalUserRepository) {
		this.generalUserRepository = generalUserRepository;
	}
	
    @Bean
    public AuditorAware<GeneralUser> customAuditorProvider() {
    	return new CustomSpringSecurityAuditorAware(this.generalUserRepository);
    }

}
