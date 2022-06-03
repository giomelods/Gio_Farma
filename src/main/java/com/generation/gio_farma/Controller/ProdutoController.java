package com.generation.gio_farma.Controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.gio_farma.Repository.CategoriaR;
import com.generation.gio_farma.Repository.ProdutoR;
import com.generation.gio_farma.model.Produto;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {


	@Autowired
	private ProdutoR produtorRepository;
	
	@Autowired
	private CategoriaR categoriarRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){ 
		return ResponseEntity.ok(produtorRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtorRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
		
}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtorRepository.findByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping 
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){ 
		if (categoriarRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtorRepository.save(produto));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
					if (produtorRepository.existsById(produto.getId())) {
		
			if (categoriarRepository.existsById(produto.getCategoria().getId())) 
				return ResponseEntity.ok(produtorRepository.save(produto));
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
					
		}
		
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		 return produtorRepository.findById(id)
				.map(obj -> {
					produtorRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
}