package com.web.apiteste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.apiteste.models.Produto;
import com.web.apiteste.repository.ProdutoRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/api")
//@Api(value = "API Rest Produtos")
//@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	public ResponseEntity <List<Produto>> listaProduto() {
		List<Produto> todos = produtoRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(todos);
	}
	
	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> listaProdutoUnico(@PathVariable long id) {
		Produto unico = produtoRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(unico);
	}
	
	@PostMapping("/produto")
	public ResponseEntity<Produto> salvaProduto(@RequestBody Produto novo) {

		Produto existente = produtoRepository.findById(novo.getId());

		if (existente != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		produtoRepository.save(novo);
		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/produto/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable long id, @RequestHeader("segredo") String password) {
		
		if(!"senha".equals(password)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Senha inválida");
		}
		
		Produto existente = produtoRepository.findById(id);

		if (existente != null) {
			produtoRepository.delete(existente);
	          return ResponseEntity.ok().body("Deletado com sucesso!");
	        }

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/produto/{id}")
	public ResponseEntity<?> updateProduto(@PathVariable long id,@RequestBody Produto novo, @RequestHeader("segredo") String password) {
		
		if(!"senha".equals(password)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Senha inválida");
		}
		
		Produto existente = produtoRepository.findById(id);
		
		if (existente != null) {
			existente.setNome(novo.getNome());
			existente.setQuantidade(novo.getQuantidade());
			existente.setValor(novo.getValor());
			
			produtoRepository.save(existente);

			return ResponseEntity.ok().body("Atualizado com sucesso!");

		}

		return ResponseEntity.notFound().build();
	}

}
