package br.com.zevolution.imdb.domain.dto.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUserUpdateDTORequest {
	
	@NotNull
	@Length(min = 3, max = 100)
	private String name;

}
