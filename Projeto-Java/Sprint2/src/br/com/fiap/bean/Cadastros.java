package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;

public class Cadastros {
	private List<Usuario> usuarios;

	public Cadastros() {
		this.usuarios = new ArrayList<>();
	}

	public Cadastros(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void adicionarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public Usuario validarUsuario(String email, String senha) {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}
		return null;
	}
}
