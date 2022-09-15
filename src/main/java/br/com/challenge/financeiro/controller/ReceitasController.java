package br.com.challenge.financeiro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.challenge.financeiro.controller.dto.FormReceitasDto;
import br.com.challenge.financeiro.controller.dto.ReceitasDto;
import br.com.challenge.financeiro.controller.form.ReceitasForm;
import br.com.challenge.financeiro.model.Receitas;
import br.com.challenge.financeiro.repository.ReceitasRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {
	
	@Autowired
	private ReceitasRepository repository;
	
	@GetMapping
	public List<ReceitasDto> lista(){
		List<Receitas> receitas = repository.findAll();
		return ReceitasDto.converter(receitas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitasDto> detalhar(@PathVariable Long id){
		Optional<Receitas> receitas = repository.findById(id);
		if(receitas.isPresent()) {
			return ResponseEntity.ok(new ReceitasDto(receitas.get()));
		}
		return ResponseEntity.badRequest().build();
	}	
	
	@PostMapping
	public ResponseEntity<FormReceitasDto> cadastra(@RequestBody @Valid ReceitasForm form, UriComponentsBuilder uriBilder) {
		Receitas receita = form.converter();
		repository.save(receita);
		
		URI uri = uriBilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
		return ResponseEntity.created(uri).body(new FormReceitasDto(receita));
		
	}
	

}
