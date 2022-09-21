package com.team.art.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.User;

@Repository
public interface UserReopsitory extends JpaRepository<User,Long> {
	User findByEmail(String email);
   List<User>findByType(String type);

}
