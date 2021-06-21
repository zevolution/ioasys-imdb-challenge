package br.com.zevolution.imdb.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Where(clause = "profile_level = 0")
public class Administrator extends SystemUser {

	private static final long serialVersionUID = 7922545003392449773L;
	
	public Administrator() {
		this.profileLevel = UserProfileLevel.ADMINISTRATOR;
	}
}
