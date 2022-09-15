package br.com.challenge.financeiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import br.com.challenge.financeiro.model.Receitas;

public class ReceitasDto {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	
	
	public ReceitasDto(Receitas receitas) {
		this.id = receitas.getId();
		this.descricao = receitas.getDescicao();
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


	public static List<ReceitasDto> converter(List<Receitas> receitas) {
		return receitas.stream().map(ReceitasDto::new).collect(Collectors.toList());
	}
	
	

}









