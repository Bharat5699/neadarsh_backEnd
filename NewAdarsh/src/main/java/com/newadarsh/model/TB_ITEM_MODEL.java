package com.newadarsh.model;

/**
 * 
 */


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author pavan.solapure
 *
 */

public class TB_ITEM_MODEL {

	
	private Long id;
	private String name;
	

	private Integer totalRecords;
	private Integer totalFilterRecords;
	private Integer rn;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	

	/**
	 * @param salary
	 *            the salary to set
	 */

	/**
	 * @return the totalRecords
	 */
	public Integer getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getRn() {
		return rn;
	}

	public void setRn(Integer rn) {
		this.rn = rn;
	}

	public Integer getTotalFilterRecords() {
		return totalFilterRecords;
	}

	public void setTotalFilterRecords(Integer totalFilterRecords) {
		this.totalFilterRecords = totalFilterRecords;
	}

	
	
}
