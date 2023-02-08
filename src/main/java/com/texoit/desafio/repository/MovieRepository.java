package com.texoit.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.desafio.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
}
