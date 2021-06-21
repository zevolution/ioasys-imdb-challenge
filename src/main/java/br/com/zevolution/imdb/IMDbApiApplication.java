package br.com.zevolution.imdb;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.zevolution.imdb.config.auditing.EnableCustomJpaAuditing;
import br.com.zevolution.imdb.config.security.EnableAPISecurity;
import br.com.zevolution.imdb.config.swagger.EnableSwagger;

@SpringBootApplication
@EnableSwagger
@EnableAPISecurity
@EnableCustomJpaAuditing
public class IMDbApiApplication {

	public static void main(String[] args) {
		/*
		 * Used to force locale JVM, to messages errors of class javax.validation.ConstraintViolationException, ex:
		 * 
		 * javax.validation.constraints.AssertFalse.message=
		 * javax.validation.constraints.AssertTrue.message=
		 * javax.validation.constraints.DecimalMax.message=
		 * javax.validation.constraints.DecimalMin.message=
		 * javax.validation.constraints.Digits.message=
		 * javax.validation.constraints.Future.message=
		 * javax.validation.constraints.Max.message=
		 * javax.validation.constraints.Min.message=
		 * javax.validation.constraints.NotNull.message=
		 * javax.validation.constraints.Null.message=
		 * javax.validation.constraints.Past.message=
		 * javax.validation.constraints.Pattern.message=
		 * javax.validation.constraints.Size.message=
		 * 
		 */
		Locale.setDefault(new Locale("en", "US"));
		SpringApplication.run(IMDbApiApplication.class, args);
	}

}
