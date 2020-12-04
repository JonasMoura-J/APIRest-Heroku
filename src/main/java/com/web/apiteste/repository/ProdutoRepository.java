package com.web.apiteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.apiteste.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	Produto findById(long id);
}
