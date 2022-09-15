package br.com.challenge.financeiro.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import br.com.challenge.financeiro.model.Receitas;

public class ReceitasForm {
	
	@NotBlank @Size(min = 5, max = 50)
	private String descricao;
	
	@NotNull @PositiveOrZero
	private BigDecimal valor;
	
	@NotNull @PastOrPresent
	private LocalDate data;
	

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
	
	
	public Receitas converter() {
		return new Receitas(descricao, valor, data);
	}
	
	

}
