package com.web.apiteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.apiteste.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Categoria findById(long id);
}
