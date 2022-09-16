package br.com.challenge.financeiro.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.challenge.financeiro.model.CategoriaDespesas;
import br.com.challenge.financeiro.model.Despesas;
import br.com.challenge.financeiro.repository.DespesasRepository;

public class DespesasForm {
	
	@NotBlank @Size(min = 5, max = 50)
	private String descricao;
	
	@NotNull @PositiveOrZero
	private BigDecimal valor;
	
	@NotNull @PastOrPresent
	private LocalDate data;
	
	@NotNull
	private CategoriaDespesas categoria;
	
	
	
	
	public CategoriaDespesas getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDespesas categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor; 
	}	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	public Despesas converter() {
		return new Despesas(descricao, valor, data, categoria);
	}
	public Despesas atuliza(Long id, DespesasRepository repository) {
		Despesas despesas = repository.getReferenceById(id);
		despesas.setDescricao(this.descricao);
		despesas.setValor(this.valor);
		despesas.setData(this.data);
		despesas.setCategoria(this.categoria);
		
		return despesas;
	}
	
	

}
