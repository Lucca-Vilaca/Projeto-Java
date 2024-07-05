package br.com.fiap.bean;

public class Usuario {

	// Atributos
	private String usuario;
	private String senha;
	private String email;

	// Construtores sem e com parâmetors

	public Usuario(String user, String senha, String email) {
		this.usuario = user;
		this.senha = senha;
		this.email = email;
	}

	public Usuario() {
	}

	// Métodos Getters & setters
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String user) {
		this.usuario = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
