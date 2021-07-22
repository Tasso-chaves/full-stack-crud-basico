package br.com.escola.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.dao.UsuarioDao;
import br.com.escola.exceptions.CriptoException;
import br.com.escola.exceptions.EmailException;
import br.com.escola.model.Usuario;
import br.com.escola.util.Util;

@Service
public class ServiceUsuario {

	@Autowired
	private UsuarioDao repositorioUsuario;
	
	public void salvarUsuario(Usuario usuario) throws Exception{
		try {
			if(repositorioUsuario.findByEmail(usuario.getEmail()) != null) {
				throw new EmailException("Já existe um email cadastrado para: " + usuario.getEmail());
			}
			usuario.setSenha(Util.md5(usuario.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			throw new CriptoException("Erro na criptografia da senha");
		}
		
		repositorioUsuario.save(usuario);
	}
	
	public Usuario loginUser(String nome, String senha) throws ServiceLoginException{
		
		Usuario userLogin = repositorioUsuario.buscarLogin(nome, senha);
		return userLogin;
	}
}
