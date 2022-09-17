package br.com.challenge.financeiro.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import br.com.challenge.financeiro.model.Despesas;

@EnableJpaRepositories
public interface DespesasRepository extends JpaRepository<Despesas, Long> {
	
	List<Despesas> findByDescricao(String descricao);

	List<Despesas> findByDataBetween(LocalDate dataInicio, LocalDate dataFinal);
	
	@Query(value = "SELECT d.categoria, SUM(d.valor) FROM Despesas d WHERE d.data >= :dataInicio AND d.data <= :dataFinal GROUP BY categoria", nativeQuery = true)
	public Collection<?> findTotalPorCategoria(@Param("dataInicio") LocalDate dataInicio, @Param("dataFinal") LocalDate dataFinal);
	
	@Query(value = "SELECT SUM(d.valor) FROM Despesas d WHERE d.data >= :dataInicio AND d.data <= :dataFinal", nativeQuery = true)
	public Optional<BigDecimal> sumBetweenData(@Param("dataInicio") LocalDate dataInicio, @Param("dataFinal") LocalDate dataFinal);
	
		
		



}
