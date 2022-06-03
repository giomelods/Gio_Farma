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
import com.generation.gio_farma.model.Categoria;


@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	

	
	@Autowired
	private CategoriaR categoriarRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){ 
		return ResponseEntity.ok(categoriarRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoriarRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
		
}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String descricao){
	return ResponseEntity.ok(categoriarRepository.findAllByNomeContainingIgnoreCase(descricao));
	}
	
	
	
		@PostMapping
		public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriarRepository.save(categoria));	
		}
	
		@PutMapping
		public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria) {
						
			return categoriarRepository.findById(categoria.getId())
					.map(resposta -> ResponseEntity.ok().body(categoriarRepository.save(categoria)))
					.orElse(ResponseEntity.notFound().build());

		}
					
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
		 return categoriarRepository.findById(id)
				.map(obj -> {
					categoriarRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
