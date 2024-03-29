package com.generation.gio_farma.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.gio_farma.model.Categoria;

@Repository
public interface CategoriaR extends JpaRepository<Categoria, Long> {

	public List <Categoria> findAllByNomeContainingIgnoreCase(@Param("tipo") String nome);
	
	public List <Categoria> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
