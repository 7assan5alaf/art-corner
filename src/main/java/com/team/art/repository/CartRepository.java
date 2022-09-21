package com.team.art.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.Cart;
import com.team.art.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
	

	List<Cart>findAllByUserOrderByCreateDateDesc(User user);
}
