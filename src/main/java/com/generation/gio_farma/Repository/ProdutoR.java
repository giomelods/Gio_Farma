package com.generation.gio_farma.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.gio_farma.model.Produto;

@Repository
public interface ProdutoR extends JpaRepository<Produto, Long>{

	
	public List <Produto> findByNomeContainingIgnoreCase(@Param("nome") String nome);
	
	public List <Produto> findByNomeAndLaboratorio(String nome, String laboratorio);
	
	public List <Produto> findByNomeOrLaboratorio(String nome, String laboratorio);
	
	public List<Produto> findByPrecoIn(List<BigDecimal> preco);
}
