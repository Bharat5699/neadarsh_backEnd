package com.newadarsh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newadarsh.model.TB_ITEM;

@Repository
public interface ItemRepository extends JpaRepository<TB_ITEM, Long> {
	  Optional<TB_ITEM> findByName(String name);

	  Boolean existsByName(String name);

	 
	  
	}
