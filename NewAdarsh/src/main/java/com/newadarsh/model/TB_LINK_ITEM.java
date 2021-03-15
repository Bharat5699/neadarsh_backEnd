package com.newadarsh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(	name = "link_item", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "id")
		})
public class TB_LINK_ITEM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long item_id;
	private long subitem_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public long getSubitem_id() {
		return subitem_id;
	}
	public void setSubitem_id(long subitem_id) {
		this.subitem_id = subitem_id;
	}
	
	
}
