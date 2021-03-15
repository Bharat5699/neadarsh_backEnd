package com.newadarsh.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newadarsh.model.order.TB_ORDER;

@Repository
public interface OrderRepository extends JpaRepository<TB_ORDER, Long> {

}
