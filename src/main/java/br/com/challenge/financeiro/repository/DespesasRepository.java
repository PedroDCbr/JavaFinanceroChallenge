package br.com.challenge.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.financeiro.model.Despesas;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {

}
