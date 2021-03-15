/**
 * 
 */
package com.newadarsh.repository;

import java.util.List;

import com.newadarsh.model.TB_ITEM;
import com.newadarsh.model.TB_LINK_ITEM;
import com.newadarsh.model.TB_SUBITEM;
import com.newadarsh.model.order.TB_ORDER;


/**
 * @author pavan.solapure
 *
 */
public interface GenericRepo {
	
	List<TB_ITEM> getItemslist(String qry);
	 List<TB_SUBITEM> getSubItemslist(String qry);
//	
	List<TB_LINK_ITEM> getlinkItemslist(String qry);
	List<TB_ORDER> getOrderList(String qry);
	
}
