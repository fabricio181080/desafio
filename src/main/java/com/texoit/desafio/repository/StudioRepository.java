package com.texoit.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.desafio.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> {

}
