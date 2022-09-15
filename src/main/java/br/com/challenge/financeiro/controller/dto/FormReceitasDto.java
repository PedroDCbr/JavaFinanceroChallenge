package br.com.challenge.financeiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.challenge.financeiro.model.Receitas;

public class FormReceitasDto {
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	
	
	
	
	public FormReceitasDto(Receitas receitas) {
		this.id = receitas.getId();
		this.descricao = receitas.getDescricao();
		this.valor = receitas.getValor();
		this.data = receitas.getData();
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
}
