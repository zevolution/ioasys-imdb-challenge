package br.com.zevolution.imdb.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Director extends BaseEntity {
	
	private static final long serialVersionUID = -4277338956766392390L;

	@Column
	private String name;

}
