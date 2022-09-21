package com.team.art.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team.art.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
   List<Product>findByCategories_Id(Long id);
   List<Product>findByUser_id(Long id);
   @Query("select p from Product p where " +"concat(p.name,p.price)"
           +"like %?1%")
    Page<Product> search(String keyword, Pageable page);
   
}
