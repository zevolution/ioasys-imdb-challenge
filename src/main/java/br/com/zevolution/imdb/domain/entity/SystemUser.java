package br.com.zevolution.imdb.domain.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import br.com.zevolution.imdb.domain.listener.UserEncodePasswordListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@EntityListeners(UserEncodePasswordListener.class)
public class SystemUser extends BaseEntity {

	private static final long serialVersionUID = 7922545003392449773L;

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column
	@NotNull
	private String name;
	
	@Column(unique = true)
	@NotNull
	private String login;
	
	@Column(unique = true)
	@NotNull
	private String email;
	
	@Column
	@NotNull
	private String password;
	
	@Column(columnDefinition = "TINYINT")
	protected UserProfileLevel profileLevel;

}
