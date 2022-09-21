package com.team.art.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.Categories;
@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long>{

}
