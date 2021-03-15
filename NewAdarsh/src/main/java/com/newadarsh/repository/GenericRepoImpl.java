
package com.newadarsh.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.newadarsh.model.TB_ITEM;
import com.newadarsh.model.TB_LINK_ITEM;
import com.newadarsh.model.TB_SUBITEM;
import com.newadarsh.model.order.TB_ORDER;


/**
 * @author pavan.solapure
 *
 */
@Repository
public class GenericRepoImpl implements GenericRepo {
	
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	/* (non-Javadoc)
//	 * @see com.opencodez.repo.GenericRepo#getUserModel()
//	 */
//	@Override
//	public List<TB_ITEM_MODEL> getUserModel() {
//
//		String qry = "SELECT id, name, 1 as total_records FROM items";
//		Query query = entityManager.createNativeQuery(qry,
//				TB_ITEM_MODEL.class);
//
//		@SuppressWarnings("unchecked")
//		List<TB_ITEM_MODEL> daoDtolist = query.getResultList();
//		
//		return daoDtolist;
//	}
	@Autowired
	JdbcTemplate template;
	
	public List<TB_ITEM> getItemslist(String qry) {
System.out.println("qrr==="+qry);
		return template.query(qry,new RowMapper<TB_ITEM>(){    
	        public TB_ITEM mapRow(ResultSet rs, int row) throws SQLException {    
	        	TB_ITEM e=new TB_ITEM();    
	            e.setId(rs.getInt(2));    
	            e.setName(rs.getString(1));    
	            e.setTotalRecords(rs.getInt(3));    
	            e.setTotalFilterRecords(rs.getInt(4));  
	            e.setRn(rs.getInt(5));    
	            return e;    
	        }    
	    });    
	}
	
	
	
	public List<TB_SUBITEM> getSubItemslist(String qry) {
		System.out.println("qrr==="+qry);
				return template.query(qry,new RowMapper<TB_SUBITEM>(){    
			        public TB_SUBITEM mapRow(ResultSet rs, int row) throws SQLException {    
			        	TB_SUBITEM e=new TB_SUBITEM();    
			            e.setId(rs.getInt(2));    
			            e.setName(rs.getString(1));    
			            e.setTotalRecords(rs.getInt(3));    
			            e.setTotalFilterRecords(rs.getInt(4));  
			            e.setRn(rs.getInt(5));    
			            return e;    
			        }    
			    });    
			}

	@Override
	public List<TB_LINK_ITEM> getlinkItemslist(String qry) {
		System.out.println("qrr==="+qry);
				return template.query(qry,new RowMapper<TB_LINK_ITEM>(){    
			        public TB_LINK_ITEM mapRow(ResultSet rs, int row) throws SQLException {    
			        	TB_LINK_ITEM e=new TB_LINK_ITEM();    
			            e.setId(rs.getLong(1));    
			            e.setItem_id(rs.getLong(2));    			         
			            e.setSubitem_id(rs.getLong(3));    
			            return e;    
			        }    
			    });    
			}
	
	
	@Override
	public List<TB_ORDER> getOrderList(String qry) {
		System.out.println("qrr==="+qry);
				return template.query(qry,new RowMapper<TB_ORDER>(){    
			        public TB_ORDER mapRow(ResultSet rs, int row) throws SQLException {    
			        	TB_ORDER e=new TB_ORDER();    
			            e.setId(rs.getInt(1));    
			            e.setOrder_no(rs.getString(2));
			            e.setName(rs.getString(3));  
			            e.setFrom_date(rs.getTimestamp(4));  
			            e.setTo_date(rs.getTimestamp(5)); 
			            e.setMobile_no(new BigInteger(rs.getString(6)));  
			            e.setAddress(rs.getString(7));
//			            e.setTotalRecords(rs.getInt(3));    
//			            e.setTotalFilterRecords(rs.getInt(4));  
//			            e.setRn(rs.getInt(5));    
			            return e;    
			        }    
			    });   
			}
}
