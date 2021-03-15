package com.newadarsh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newadarsh.model.TB_LINK_ITEM;

@Repository
public interface LinkItemRepository extends JpaRepository<TB_LINK_ITEM, Long>{
	@Modifying
	@Query("delete from TB_LINK_ITEM b where b.item_id=:item_id")
	void deleteLinkItem(@Param("item_id") long item_id);
	
	@Query("select b.id,b.item_id,b.subitem_id from TB_LINK_ITEM b where b.item_id=:item_id")
	List<TB_LINK_ITEM> findByItem_id(@Param("item_id") long item_id);

	
}
