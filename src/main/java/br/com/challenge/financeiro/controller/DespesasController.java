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
import br.com.challenge.financeiro.controller.dto.DespesasDto;
import br.com.challenge.financeiro.controller.dto.FormDespesasDto;
import br.com.challenge.financeiro.controller.form.DespesasForm;
import br.com.challenge.financeiro.model.Despesas;
import br.com.challenge.financeiro.repository.DespesasRepository;

@RestController
@RequestMapping("/despesas")
public class DespesasController {
	
	@Autowired
	private DespesasRepository repository;
	
	@GetMapping
	public List<DespesasDto> lista(){
		List<Despesas> despesas = repository.findAll();
		return DespesasDto.converter(despesas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesasDto> detalhar(@PathVariable Long id){
		Optional<Despesas> despesas = repository.findById(id);
		if(despesas.isPresent()) {
			return ResponseEntity.ok(new DespesasDto(despesas.get()));
		}
		return ResponseEntity.badRequest().build();
	}	
	
	@PostMapping
	public ResponseEntity<FormDespesasDto> cadastra(@RequestBody @Valid DespesasForm form, UriComponentsBuilder uriBilder) {
		Despesas despesas = form.converter();
		repository.save(despesas);
		
		URI uri = uriBilder.path("/despesas/{id}").buildAndExpand(despesas.getId()).toUri();
		return ResponseEntity.created(uri).body(new FormDespesasDto(despesas));
		
	}
	

}
