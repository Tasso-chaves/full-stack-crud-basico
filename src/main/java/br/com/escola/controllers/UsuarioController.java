package br.com.escola.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.escola.model.Aluno;
import br.com.escola.model.Usuario;
import br.com.escola.service.ServiceLoginException;
import br.com.escola.service.ServiceUsuario;
import br.com.escola.util.Util;

@Controller
@RequestMapping("/")
public class UsuarioController {
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	@GetMapping
	public ModelAndView logar(Usuario usuario) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;	
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/cadastro");
		return mv;
		
	}

	@PostMapping("salvarUsuario")
	public ModelAndView cadastrar(Usuario usuario) throws Exception {
		ModelAndView mv = new ModelAndView();
		serviceUsuario.salvarUsuario(usuario);
		mv.setViewName("redirect:/");
		return mv;		
	}
	
	@PostMapping("/login")
	public ModelAndView loginSistema(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceLoginException{
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("usuario", new Usuario());
		if(br.hasErrors()) {
			mv.setViewName("login/login");
		}
		
		Usuario userLogin = serviceUsuario.loginUser(usuario.getNome(), Util.md5(usuario.getSenha()));
		
		if(userLogin == null) {
			mv.addObject("msg", "Usuario não encontrado! Tente novamente..");
			mv.setViewName("login/login");
		}else {
			session.setAttribute("usuarioLogado", userLogin);
			return index();
		}
		
		return mv;
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/login";
	}
	
}
