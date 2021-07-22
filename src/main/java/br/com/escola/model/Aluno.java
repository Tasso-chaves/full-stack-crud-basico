package br.com.escola.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.escola.enums.Curso;
import br.com.escola.enums.Status;

@Entity
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@NotNull(message = "O campo não pode ser nulo")
	@NotBlank(message = "Não pode")
	@Size(min=4, message = "Nome pequeno")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Curso curso;
	
	@NotNull(message = "Clique em gerar matricula ")
	@Size(min=4, message = "Clique em gerar matricula.")
	private String matricula;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo não pode ser nulo")
	private Status status;
	
	@NotBlank(message = "O campo turno não pode se vazio")
	@Size(min=4, message = "No minimo 4 caracteres.")
	@NotNull(message = "O campo não pode ser nulo")
	private String turno;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
