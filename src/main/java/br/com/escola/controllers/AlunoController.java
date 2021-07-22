package br.com.escola.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.escola.dao.AlunoDao;
import br.com.escola.model.Aluno;

@Controller
public class AlunoController {
	
	@Autowired
	private AlunoDao alunorepositorio;
	
	@GetMapping("/inserirAlunos")
	public ModelAndView insertAlunos(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/formAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@PostMapping("insertAlunos")
	public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("aluno/formAluno");
			mv.addObject(aluno);
		}else {
			mv.setViewName("redirect:/alunos-adicionados");
			alunorepositorio.save(aluno);
		}
		
		return mv;
	}
	
	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/listAlunos");
		mv.addObject("alunosList", alunorepositorio.findAll());
		return mv;
	}
	
	@GetMapping("alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alterar");
		Aluno aluno = alunorepositorio.getOne(id);
		mv.addObject("aluno", aluno);
		return mv;	
	}
	
	@PostMapping("alterar")
	public String alterar(Aluno aluno) {
		//ModelAndView mv = new ModelAndView();
		alunorepositorio.save(aluno);
		//mv.setViewName("redirect:/alunos-adicionados");
		return "redirect:/alunos-adicionados";	
	}

	@GetMapping("excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) {
		alunorepositorio.deleteById(id);
		return "redirect:/alunos-adicionados";	
	}
	
	@GetMapping("filtros-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/filtroAluno");
		return mv;
	}
	
	@GetMapping("alunos-ativos")
	public ModelAndView listaAlunosAtivos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunos-ativos");
		mv.addObject("alunosAtivos", alunorepositorio.findByStatusAtivo());
		return mv;
	}
	@GetMapping("alunos-inativos")
	public ModelAndView listaAlunosInativos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunos-inativos");
		mv.addObject("alunosInativos", alunorepositorio.findByStatusInativo());
		return mv;
	}
	
	@PostMapping("pesquisar-aluno")
	public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		List<Aluno> listAlunos;
		if(nome == null || nome.trim().isEmpty()) {
			listAlunos = alunorepositorio.findAll();
		}else {
			listAlunos = alunorepositorio.findByNomeContainingIgnoreCase(nome);
		}
		mv.addObject("listaDeAlunos", listAlunos);
		mv.setViewName("aluno/pesquisa-resultado");
		return mv;
	
	}
		
}
