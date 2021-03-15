package com.newadarsh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(	name = "sub_item", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "name")
		})
public class TB_SUBITEM {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@Transient
	private Integer totalRecords;
	@Transient
	private Integer totalFilterRecords;
	@Transient
	private Integer rn;
	
	public long getId() {
		return id;
	}
	public TB_SUBITEM() {
		super();
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TB_SUBITEM(String name) {
		super();
		this.name = name;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Integer getTotalFilterRecords() {
		return totalFilterRecords;
	}
	public void setTotalFilterRecords(Integer totalFilterRecords) {
		this.totalFilterRecords = totalFilterRecords;
	}
	public Integer getRn() {
		return rn;
	}
	public void setRn(Integer rn) {
		this.rn = rn;
	}
	
	
}
