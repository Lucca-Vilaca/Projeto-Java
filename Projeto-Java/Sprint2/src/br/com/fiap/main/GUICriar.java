package br.com.fiap.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import br.com.fiap.bean.Usuario;

// Define a classe GUICriar que herda de JPanel e será usada para criar a interface gráfica de cadastro
@SuppressWarnings("serial")
public class GUICriar extends JPanel {

	// Declaração dos componentes da interface gráfica
	private JLabel lbTitulo, lbUsuario, lbSenha, lbNome;
	private JTextField tfUsuario, tfNome;
	private JPasswordField pfSenha;
	private JButton btCadastrar;
	private List<Usuario> usuarios; // Lista de usuários cadastrados
	private CadastroListener cadastroListener; // Listener para o evento de cadastro

	// Construtor da classe que inicializa os componentes e define os eventos
	public GUICriar(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		inicializarComponentes();
		definirEventos();
	}

	// Método para inicializar os componentes da interface gráfica
	private void inicializarComponentes() {
		setLayout(null); // Define o layout como nulo para posicionar os componentes manualmente
		setBackground(Color.GRAY); // Define a cor de fundo do painel

		// Inicializa os rótulos e define suas propriedades
		lbTitulo = new JLabel("Cadastro", JLabel.CENTER);
		lbTitulo.setForeground(Color.WHITE);
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 18));

		lbNome = new JLabel("Nome:");
		lbNome.setForeground(Color.WHITE);

		lbUsuario = new JLabel("Usuário:");
		lbUsuario.setForeground(Color.WHITE);

		lbSenha = new JLabel("Senha:");
		lbSenha.setForeground(Color.WHITE);

		// Inicializa os campos de texto e senha
		tfNome = new JTextField();
		tfUsuario = new JTextField();
		pfSenha = new JPasswordField();

		// Inicializa o botão de cadastro
		btCadastrar = new JButton("Cadastrar");

		// Adiciona os componentes ao painel
		add(lbTitulo);
		add(lbNome);
		add(lbUsuario);
		add(lbSenha);
		add(tfNome);
		add(tfUsuario);
		add(pfSenha);
		add(btCadastrar);

		// Define as posições e tamanhos dos componentes
		lbTitulo.setBounds(50, 30, 200, 30);
		lbNome.setBounds(50, 80, 100, 30);
		lbUsuario.setBounds(50, 130, 100, 30);
		lbSenha.setBounds(50, 180, 100, 30);
		tfNome.setBounds(150, 80, 200, 30);
		tfUsuario.setBounds(150, 130, 200, 30);
		pfSenha.setBounds(150, 180, 200, 30);
		btCadastrar.setBounds(150, 230, 100, 30);
	}

	// Método para definir os eventos dos componentes
	private void definirEventos() {
		btCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtém os valores dos campos de texto e senha
				String nome = tfNome.getText();
				String usuario = tfUsuario.getText();
				String senha = String.valueOf(pfSenha.getPassword());

				// Verifica se algum campo está vazio
				if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro no Cadastro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica se o usuário já existe
				if (existeUsuario(usuario)) {
					JOptionPane.showMessageDialog(null, "Usuário já cadastrado! Escolha outro usuário.",
							"Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Cria um novo usuário
				Usuario novoUsuario = new Usuario(usuario, senha, nome);

				// Adiciona o novo usuário à lista de usuários
				usuarios.add(novoUsuario);

				// Mostra uma mensagem de sucesso
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Cadastro",
						JOptionPane.INFORMATION_MESSAGE);

				// Notifica o listener que o cadastro foi feito, se houver um listener
				// registrado
				if (cadastroListener != null) {
					cadastroListener.onCadastro(novoUsuario);
				}

				// Limpa os campos após o cadastro
				tfNome.setText("");
				tfUsuario.setText("");
				pfSenha.setText("");

				// Troca para a interface de login (GUILogar)
				trocarParaGUILogar();
			}
		});
	}

	// Método para verificar se um usuário já está cadastrado
	private boolean existeUsuario(String usuario) {
		for (Usuario u : usuarios) {
			if (u.getUsuario().equals(usuario)) {
				return true;
			}
		}
		return false;
	}

	// Método para trocar a interface para a tela de login (GUILogar)
	private void trocarParaGUILogar() {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.getContentPane().removeAll();
		GUILogar guiLogin = new GUILogar(usuarios);
		guiLogin.setLoginListener(new LoginListener() {
			@Override
			public void onLogin(Usuario usuario) {
				trocarParaGUIInicio(usuario);
			}
		});
		topFrame.getContentPane().add(guiLogin);
		topFrame.revalidate();
		topFrame.repaint();
	}

	// Método para trocar a interface para a tela inicial (GUIInicio)
	private void trocarParaGUIInicio(Usuario usuario) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.getContentPane().removeAll();
		GUIInicio guiInicio = new GUIInicio(usuario);
		topFrame.getContentPane().add(guiInicio);
		topFrame.revalidate();
		topFrame.repaint();
	}

	// Método para definir o listener de cadastro
	public void setCadastroListener(CadastroListener listener) {
		this.cadastroListener = listener;
	}
}
