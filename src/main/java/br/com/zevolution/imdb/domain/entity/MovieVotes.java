package br.com.zevolution.imdb.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MovieVotes extends BaseEntity {
	
	private static final long serialVersionUID = 6965195879438811202L;
	
	@Column
	private Integer score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
	
}
