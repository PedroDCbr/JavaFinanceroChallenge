package br.com.challenge.financeiro.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.challenge.financeiro.model.Receitas;

@RunWith(SpringRunner.class)
@DataJpaTest
class ReceitasRepositoryTest {
	
	@Autowired
	private ReceitasRepository repositort;
	
	
	@Test
	void buscarPorDescricao() {
		String descricao = "Salário";
		Page<Receitas> receita = repositort.findByDescricao(descricao, null);
		receita.forEach(receitas ->{
			if(receitas.getDescricao() == descricao) {
				assertEquals(receitas.getDescricao(), descricao);
			}
		});
		assertNotNull(receita);
		;
	}
	
	
	
	
	

}
