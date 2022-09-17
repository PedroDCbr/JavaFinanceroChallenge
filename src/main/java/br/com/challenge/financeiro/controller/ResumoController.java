package br.com.challenge.financeiro.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.challenge.financeiro.controller.dto.ResumoDto;
import br.com.challenge.financeiro.repository.DespesasRepository;
import br.com.challenge.financeiro.repository.ReceitasRepository;


@RestController
@RequestMapping("/resumo")
public class ResumoController {
	
	@Autowired
	public DespesasRepository despesas;
	
	@Autowired
	public ReceitasRepository receitas;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<ResumoDto> resumo(@PathVariable Integer ano, @PathVariable Integer mes) {
		LocalDate dataInicio;
		try {
			dataInicio = LocalDate.of(ano, mes, 1);
		} catch (DateTimeException e) {
			return ResponseEntity.badRequest().build();
		}

		LocalDate dataFinal = dataInicio.with(TemporalAdjusters.lastDayOfMonth());

		ResumoDto resumo = new ResumoDto(dataInicio, dataFinal, receitas, despesas);

		return ResponseEntity.ok(resumo);
		
		
	}

}
