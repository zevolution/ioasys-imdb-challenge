package br.com.zevolution.imdb.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "USER")
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Immutable
public class GeneralUser extends SystemUser {

	private static final long serialVersionUID = 7922545003392449773L;
	
}
