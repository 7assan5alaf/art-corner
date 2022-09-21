package com.team.art.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.team.art.model.AuthToken;
import com.team.art.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthToken,Long> {
	AuthToken findByUser(User user);
	AuthToken findByToken(String token);
}
