package br.com.challenge.financeiro.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.challenge.financeiro.model.Despesas;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {
	
	List<Despesas> findByDescricao(String descricao);

	List<Despesas> findByDataBetween(LocalDate dataInicio, LocalDate dataFinal);
	
		
		



}
