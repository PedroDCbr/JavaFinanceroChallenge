package br.com.challenge.financeiro.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import br.com.challenge.financeiro.model.Receitas;

@EnableJpaRepositories
public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

	Page<Receitas> findByDescricao(String descricao, Pageable pageable);

	Page<Receitas> findByDataBetween(LocalDate dataInicio, LocalDate dataFinal, Pageable pageable);
	
	
	@Query(value = "SELECT SUM(r.valor) FROM Receitas r WHERE r.data >= :dataInicio AND r.data <= :dataFinal", nativeQuery = true)
	public Optional<BigDecimal> sumBetweenData(@Param("dataInicio") LocalDate dataInicio, @Param("dataFinal") LocalDate dataFinal);

}
