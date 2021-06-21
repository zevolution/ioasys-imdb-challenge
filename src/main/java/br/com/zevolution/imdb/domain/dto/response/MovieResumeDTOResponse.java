package br.com.zevolution.imdb.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResumeDTOResponse {
	
	private Long id;
	private String name;
	private Double averageScore = 0.0;

}
