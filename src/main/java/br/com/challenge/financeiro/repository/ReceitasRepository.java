package br.com.challenge.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.financeiro.model.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

}
