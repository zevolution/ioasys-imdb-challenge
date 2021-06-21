package br.com.zevolution.imdb.config.swagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@EnableSwagger2
public @interface EnableSwagger {

}
