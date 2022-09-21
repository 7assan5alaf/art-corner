package com.team.art.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
}
