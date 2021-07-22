package br.com.escola.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.escola.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("select u from Usuario u where u.nome = :nome and u.senha = :senha")
	public Usuario buscarLogin(String nome, String senha);

}
