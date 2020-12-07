package com.web.apiteste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.apiteste.models.Categoria;
import com.web.apiteste.models.Produto;
import com.web.apiteste.repository.CategoriaRepository;

@RestController
@RequestMapping(value="/api")
//@Api(value = "API Rest Produtos")
//@CrossOrigin(origins = "*")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/categoria")
	public ResponseEntity <List<Categoria>> listaCategoria() {
		List<Categoria> todos = categoriaRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(todos);
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> listaCategoriaUnica(@PathVariable long id) {
		Categoria unico = categoriaRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(unico);
	}
	
	@PostMapping("/categoria")
	public ResponseEntity<Categoria> salvaCategoria(@RequestBody Categoria novo) {

		Categoria existente = categoriaRepository.findById(novo.getId());

		if (existente != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		categoriaRepository.save(novo);
		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/categoria/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable long id, @RequestHeader("segredo") String password) {
		
		if(!"senha".equals(password)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Senha inválida");
		}
		
		Categoria existente = categoriaRepository.findById(id);

		if (existente != null) {
			categoriaRepository.delete(existente);
	          return ResponseEntity.ok().body("Deletado com sucesso!");
	        }

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/categoria/{id}")
	public ResponseEntity<?> updateProduto(@PathVariable long id,@RequestBody Produto novo, @RequestHeader("segredo") String password) {
		
		if(!"senha".equals(password)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Senha inválida");
		}
		
		Categoria existente = categoriaRepository.findById(id);
		
		if (existente != null) {
			existente.setNome(novo.getNome());
			
			categoriaRepository.save(existente);

			return ResponseEntity.ok().body("Atualizado com sucesso!");

		}

		return ResponseEntity.notFound().build();
	}

}
