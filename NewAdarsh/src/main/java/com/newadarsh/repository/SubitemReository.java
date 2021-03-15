package com.newadarsh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newadarsh.model.TB_ITEM;
import com.newadarsh.model.TB_SUBITEM;
@Repository
public interface SubitemReository extends JpaRepository<TB_SUBITEM, Long> {
	  Optional<TB_ITEM> findByName(String name);

	  Boolean existsByName(String name);

	 
	  
	}
