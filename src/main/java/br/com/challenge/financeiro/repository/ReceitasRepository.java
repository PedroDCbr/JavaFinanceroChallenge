package br.com.challenge.financeiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.financeiro.model.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

	List<Receitas> findByDescricao(String descricao);

	List<Receitas> findByDataBetween(LocalDate dataInicio, LocalDate dataFinal);

}
