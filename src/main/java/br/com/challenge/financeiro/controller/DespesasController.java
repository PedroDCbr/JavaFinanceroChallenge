package br.com.challenge.financeiro.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<DespesasDto> lista(@RequestParam(required = false) String descricao) {
		if (descricao == null) {
			List<Despesas> despesas = repository.findAll();
			return DespesasDto.converter(despesas);
		} else {
			List<Despesas> despesas = repository.findByDescricao(descricao);
			return DespesasDto.converter(despesas);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesasDto> detalhar(@PathVariable Long id) {
		Optional<Despesas> despesas = repository.findById(id);
		if (despesas.isPresent()) {
			return ResponseEntity.ok(new DespesasDto(despesas.get()));
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<?> buscarPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes) {
		LocalDate dataInicio = LocalDate.of(ano, mes, 1);		
		if(dataInicio == null) {
			return ResponseEntity.badRequest().build();
		}
		LocalDate dataFinal = dataInicio.with(TemporalAdjusters.lastDayOfMonth());
		List<Despesas> despesas = repository.findByDataBetween(dataInicio, dataFinal);
		return ResponseEntity.ok(DespesasDto.converter(despesas));

	}

	@PostMapping
	public ResponseEntity<FormDespesasDto> cadastra(@RequestBody @Valid DespesasForm form,
			UriComponentsBuilder uriBilder) {
		Despesas despesas = form.converter();
		repository.save(despesas);

		URI uri = uriBilder.path("/despesas/{id}").buildAndExpand(despesas.getId()).toUri();
		return ResponseEntity.created(uri).body(new FormDespesasDto(despesas));

	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<FormDespesasDto> atualizar(@RequestBody @Valid DespesasForm form, @PathVariable Long id) {
		Optional<Despesas> optional = repository.findById(id);
		if (optional.isPresent()) {
			Despesas despesas = form.atuliza(id, repository);
			return ResponseEntity.ok(new FormDespesasDto(despesas));
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> escluir(@PathVariable Long id) {
		Optional<Despesas> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
