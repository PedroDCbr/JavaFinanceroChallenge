package br.com.challenge.financeiro.controller;

import java.net.URI;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<ReceitasDto> lista(@RequestParam(required = false) String descricao, Pageable pageable){
		if(descricao == null) {
			Page<Receitas> receitas = repository.findAll(pageable);
			return ReceitasDto.converter(receitas);
		}else {
			Page<Receitas> receitas = repository.findByDescricao(descricao, pageable);
			return ReceitasDto.converter(receitas);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitasDto> detalhar(@PathVariable Long id){
		Optional<Receitas> receitas = repository.findById(id);
		if(receitas.isPresent()) {
			return ResponseEntity.ok(new ReceitasDto(receitas.get()));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Page<ReceitasDto>> buscarPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes,
			Pageable pageable) {
		LocalDate dataInicio;
		try {
			dataInicio = LocalDate.of(ano, mes, 1);
		} catch (DateTimeException e) {
			return ResponseEntity.badRequest().build();		
		}
		
		LocalDate dataFinal = dataInicio.with(TemporalAdjusters.lastDayOfMonth());
		Page<Receitas> receitas = repository.findByDataBetween(dataInicio, dataFinal, pageable);
		return ResponseEntity.ok(ReceitasDto.converter(receitas));

	}
	
	@PostMapping
	public ResponseEntity<FormReceitasDto> cadastra(@RequestBody @Valid ReceitasForm form, UriComponentsBuilder uriBilder) {
		Receitas receitas = form.converter();
		repository.save(receitas);
		
		URI uri = uriBilder.path("/receitas/{id}").buildAndExpand(receitas.getId()).toUri();
		return ResponseEntity.created(uri).body(new FormReceitasDto(receitas));
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FormReceitasDto> atualizar(@RequestBody @Valid ReceitasForm form, @PathVariable Long id){
		Optional<Receitas> optional = repository.findById(id);
		if(optional.isPresent()) {
			Receitas receitas = form.atualiza(id, repository);
			return ResponseEntity.ok(new FormReceitasDto(receitas));
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> escluir(@PathVariable Long id){
		Optional<Receitas> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();		
		}
		return ResponseEntity.badRequest().build();
	}
	

}
