package br.com.escola.enums;

public enum Curso {
	ADMINISTRAÇÃO("Administracao"),
	INFORMATICA("Informatica"),
	DIREITO("Direito"),
	FARMACIA("Farmacia"),
	PROGRAMACAO("Programacao");
	
	private String curso;
		
	
	private Curso(String curso) {
		this.curso = curso;
	}

}
