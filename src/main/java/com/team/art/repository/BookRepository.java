package com.team.art.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
