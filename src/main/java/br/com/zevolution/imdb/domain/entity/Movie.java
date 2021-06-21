package br.com.zevolution.imdb.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Movie extends BaseEntity {
	 
	private static final long serialVersionUID = 6410488755477159225L;
	
	@Column
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Director director;
	
	@Column
	private String genre;
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Actor> actors;
	
	@Column
	@Setter(AccessLevel.NONE)
	private Long totalScore = 0L;
	
	@Column
	@Setter(AccessLevel.NONE)
	private Double averageScore = 0.0;
	
	@Column
	@Setter(AccessLevel.NONE)
	private Long amountVotes = 0L;
	
	public void vote(Integer score) {
		this.totalScore += score;
		this.amountVotes += 1;
		
		if (totalScore > 0 && this.amountVotes > 0) {
			this.averageScore = (double) (this.totalScore / this.amountVotes);
		}
	}

}
