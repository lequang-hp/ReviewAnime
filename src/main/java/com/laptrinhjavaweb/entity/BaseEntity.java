package com.laptrinhjavaweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass // Lop cha bat buoc phai co cai nay + abstract
@EntityListeners(AuditingEntityListener.class) // de dung dc jpa auditing
public abstract class BaseEntity {

	@Id // id chinh la primary key & not null
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tu dong tang Auto increment
	private Long id;
	
	@Column(name = "createddate")
	@CreatedDate
	private Date createdDate;
	
	@Column(name = "modifieddate")
	@LastModifiedDate
	private Date modifieddate;
	
	@Column(name = "createdby")
	@CreatedBy
	private String createdBy;
	
	@Column(name = "modifiedby")
	@LastModifiedBy
	private String modifiedBy;
	
	public Long getId() {
		return id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}


	public Date getModifieddate() {
		return modifieddate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}
}
