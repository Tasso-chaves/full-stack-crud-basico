package br.com.escola.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.escola.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {
	
	@Query("select a from Aluno a where a.status = 'ATIVO'")
	public List<Aluno> findByStatusAtivo();
	
	@Query("select a from Aluno a where a.status = 'INATIVO'")
	public List<Aluno> findByStatusInativo();
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);

}
