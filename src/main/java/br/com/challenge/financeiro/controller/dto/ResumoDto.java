package br.com.challenge.financeiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import br.com.challenge.financeiro.repository.DespesasRepository;
import br.com.challenge.financeiro.repository.ReceitasRepository;

public class ResumoDto {
	
	
	private BigDecimal totalReceitas;
	private BigDecimal totalDespesas;
	private BigDecimal saldo;

	private Collection<?> totalPorCategoria;

	public ResumoDto(LocalDate dataInicio, LocalDate dataFinal, ReceitasRepository receitas, DespesasRepository despesas) {

		this.totalReceitas = receitas.sumBetweenData(dataInicio, dataFinal).orElse(BigDecimal.ZERO);
		this.totalDespesas = despesas.sumBetweenData(dataInicio, dataFinal).orElse(BigDecimal.ZERO);

		this.saldo = this.totalReceitas.subtract(this.totalDespesas);

		this.totalPorCategoria = despesas.findTotalPorCategoria(dataInicio, dataFinal);
	}
	
	

	public BigDecimal getTotalReceitas() {
		return totalReceitas;
	}

	public BigDecimal getTotalDespesas() {
		return totalDespesas;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public Collection<?> getTotalPorCategoria() {
		return totalPorCategoria;
	}
	
	
}
