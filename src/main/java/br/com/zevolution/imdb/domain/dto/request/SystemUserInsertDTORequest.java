package br.com.zevolution.imdb.domain.dto.request;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUserInsertDTORequest {

	@NotNull
	@Length(min = 3, max = 100)
	private String name;
	
	@NotNull
	@Length(min = 3, max = 50)
	private String login;
	
	@NotNull
	@Length(min = 5, max = 100)
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{0,}$", message = "should be contain A-Z, a-z, 0-9 and @$!%*?&.")
	@Length(min = 8, max = 15)
	private String password;

}
