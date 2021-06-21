package br.com.zevolution.imdb.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {

	private static final long serialVersionUID = 1;

	@ManyToOne
	@JoinColumn(name = "created_by")
	@CreatedBy
    protected GeneralUser createdBy;

	@Column
    @CreatedDate
    protected LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "updated_by")
    @LastModifiedBy
    protected GeneralUser updatedBy;

	@Column
    @LastModifiedDate
    protected LocalDateTime updatedAt;

}
