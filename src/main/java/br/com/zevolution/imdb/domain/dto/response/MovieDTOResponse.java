package br.com.zevolution.imdb.domain.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTOResponse {
	
	private Long id;
	private String name;
	private DirectorDTOResponse director;
	private String genre;
	private List<ActorDTOResponse> actors = new ArrayList<>();
	private Double averageScore = 0.0;

}
