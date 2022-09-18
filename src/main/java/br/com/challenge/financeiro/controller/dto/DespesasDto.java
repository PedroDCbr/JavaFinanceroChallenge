package br.com.challenge.financeiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.challenge.financeiro.model.CategoriaDespesas;
import br.com.challenge.financeiro.model.Despesas;

public class DespesasDto {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private CategoriaDespesas categoria;
	
	
	public DespesasDto(Despesas despesas) {
		this.id = despesas.getId();
		this.descricao = despesas.getDescricao();
		this.valor = despesas.getValor();
		this.data = despesas.getData();
		this.categoria = despesas.getCategoria();
	}

	
	public CategoriaDespesas getCategoria() {
		return categoria;
	}
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public LocalDate getData() {
		return data;
	}


	public static Page<DespesasDto> converter(Page<Despesas> despesas) {
		return despesas.map(DespesasDto::new);
	}
	
	
	
	

}









