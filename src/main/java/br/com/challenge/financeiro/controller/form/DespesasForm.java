package br.com.challenge.financeiro.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.challenge.financeiro.model.Despesas;

public class DespesasForm {
	
	@NotNull @NotEmpty @Size(min = 5, max = 50)
	private String descricao;
	
	@NotNull @NotEmpty
	private BigDecimal valor;
	
	@NotNull @NotEmpty
	private LocalDate data = LocalDate.now();
	

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
	
	
	public Despesas converter() {
		return new Despesas(descricao, valor, data);
	}
	
	

}
