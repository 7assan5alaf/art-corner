package com.team.art.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order>findByUser_Id(Long id);
	List<Order> findByEmail(String email);
}
