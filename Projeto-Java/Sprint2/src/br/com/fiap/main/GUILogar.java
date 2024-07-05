package br.com.fiap.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import br.com.fiap.bean.Usuario;

// Define a classe GUILogar que herda de JPanel e será usada para a tela de login
@SuppressWarnings("serial")
public class GUILogar extends JPanel {

	// Declaração dos componentes da interface
	private JLabel lbTitulo, lbUsuario, lbSenha;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JButton btLogin;
	private List<Usuario> usuarios; // Lista de usuários cadastrados
	private LoginListener loginListener; // Listener para tratar eventos de login

	// Construtor da classe que inicializa os componentes e define os eventos
	public GUILogar(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		inicializarComponentes();
		definirEventos();
	}

	// Método para inicializar os componentes da interface gráfica
	private void inicializarComponentes() {
		setLayout(null); // Define o layout como nulo para posicionar os componentes manualmente
		setBackground(Color.GRAY); // Define a cor de fundo do painel

		// Inicializa o rótulo de título e define suas propriedades
		lbTitulo = new JLabel("Login", JLabel.CENTER);
		lbTitulo.setForeground(Color.WHITE);
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 18));

		// Inicializa os rótulos de usuário e senha
		lbUsuario = new JLabel("Usuário:");
		lbUsuario.setForeground(Color.WHITE);

		lbSenha = new JLabel("Senha:");
		lbSenha.setForeground(Color.WHITE);

		// Inicializa os campos de texto para usuário e senha
		tfUsuario = new JTextField();
		pfSenha = new JPasswordField();

		// Inicializa o botão de login
		btLogin = new JButton("Login");

		// Adiciona os componentes ao painel
		add(lbTitulo);
		add(lbUsuario);
		add(lbSenha);
		add(tfUsuario);
		add(pfSenha);
		add(btLogin);

		// Define as posições e tamanhos dos componentes
		lbTitulo.setBounds(50, 30, 200, 30);
		lbUsuario.setBounds(50, 80, 100, 30);
		lbSenha.setBounds(50, 130, 100, 30);
		tfUsuario.setBounds(150, 80, 200, 30);
		pfSenha.setBounds(150, 130, 200, 30);
		btLogin.setBounds(150, 180, 100, 30);
	}

	// Método para definir os eventos dos componentes
	private void definirEventos() {
		btLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = tfUsuario.getText();
				String senha = String.valueOf(pfSenha.getPassword());

				Usuario usuarioLogado = null;

				// Procura o usuário na lista de usuários cadastrados
				for (Usuario u : usuarios) {
					if (u.getUsuario().equals(usuario) && u.getSenha().equals(senha)) {
						usuarioLogado = u;
						break;
					}
				}

				if (usuarioLogado != null) {
					// Mostra mensagem de login bem-sucedido
					JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Login",
							JOptionPane.INFORMATION_MESSAGE);

					// Notifica o listener que o login foi feito
					if (loginListener != null) {
						loginListener.onLogin(usuarioLogado);
					}
				} else {
					// Mostra mensagem de login inválido
					JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Erro no Login",
							JOptionPane.ERROR_MESSAGE);
				}

				// Limpa os campos após o login
				tfUsuario.setText("");
				pfSenha.setText("");
			}
		});
	}

	// Método para definir o listener de login
	public void setLoginListener(LoginListener listener) {
		this.loginListener = listener;
	}
}
