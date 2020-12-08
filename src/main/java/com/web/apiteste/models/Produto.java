package com.web.apiteste.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_PRODUTO")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private BigDecimal quantidade;
	
	private BigDecimal valor;
	
	private LocalDate fabricação;
	
	@ManyToMany
	@JoinTable(name = "tb_produto_categoria",
			joinColumns = @JoinColumn(name = "produto_id", referencedColumnName =  "id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id")
	)
	private Set<Categoria> categoria = new HashSet<Categoria>();
	
	public Produto() {
		
	}
	
	public Produto(long id, String nome, BigDecimal quantidade, BigDecimal valor, LocalDate fabricação,
			Set<Categoria> categoria) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
		this.fabricação = fabricação;
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getFabricação() {
		return fabricação;
	}

	public void setFabricação(LocalDate fabricação) {
		this.fabricação = fabricação;
	}

	public Set<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(Set<Categoria> categoria) {
		this.categoria = categoria;
	}
	
}
