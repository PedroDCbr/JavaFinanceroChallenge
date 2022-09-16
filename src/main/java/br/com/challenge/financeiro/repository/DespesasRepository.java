package br.com.challenge.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.financeiro.model.Despesas;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {

	List<Despesas> findByDescricao(String descricao);

}
