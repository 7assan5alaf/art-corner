package com.team.art.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
	List<Event>findByUser_Id(Long id);
}
