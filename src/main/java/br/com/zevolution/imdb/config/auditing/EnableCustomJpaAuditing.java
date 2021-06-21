package br.com.zevolution.imdb.config.auditing;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableJpaAuditing(auditorAwareRef = "customAuditorProvider")
@Import({ CustomJpaAuditingConfig.class })
public @interface EnableCustomJpaAuditing {

}
