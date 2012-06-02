package br.flf.curso.sugestaovoto;

/**
 * Classe simples que contém os dados do curso.
 */
public class Curso {
	
	private String nome;
	private String descricao;
	private String email;
	
	/** Somatório de todos os votos efetuados para esse curso. */
	private Long qtdeVotos;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Long getQtdeVotos() {
		return qtdeVotos;
	}

	public void setQtdeVotos(Long qtdeVotos) {
		this.qtdeVotos = qtdeVotos;
	}

}
