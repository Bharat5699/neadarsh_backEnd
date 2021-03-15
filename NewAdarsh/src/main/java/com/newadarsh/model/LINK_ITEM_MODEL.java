package com.newadarsh.model;

import java.util.List;

import javax.persistence.Entity;


public class LINK_ITEM_MODEL {

	List<TB_LINK_ITEM> linkitem;

	public List<TB_LINK_ITEM> getLinkitem() {
		return linkitem;
	}

	public void setLinkitem(List<TB_LINK_ITEM> linkitem) {
		this.linkitem = linkitem;
	}
	
}
