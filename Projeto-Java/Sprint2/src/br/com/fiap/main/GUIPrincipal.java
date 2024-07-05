package br.com.fiap.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.fiap.bean.Usuario;

// A classe GUIPrincipal herda de JFrame e é a janela principal da aplicação
@SuppressWarnings("serial")
public class GUIPrincipal extends JFrame {

	// Declaração dos componentes do menu
	private JMenuBar mnBarra;
	private JMenu mnEntrar, mnSair;
	private JMenuItem miSair, miInicio, miCadastro, miLogin;
	private List<Usuario> usuarios; // Lista de usuários cadastrados
	private Usuario usuarioLogado; // Usuário logado atualmente

	// Construtor da classe GUIPrincipal
	public GUIPrincipal() {
		inicializarComponentes(); // Inicializa os componentes da interface gráfica
		definirEventos(); // Define os eventos dos componentes
		usuarios = new ArrayList<>(); // Inicializa a lista de usuários
		popularUsuarios(); // Popula a lista de usuários com dados de exemplo (temporário)
	}

	// Método para inicializar os componentes da interface gráfica
	private void inicializarComponentes() {
		setTitle("Janela Principal"); // Define o título da janela
		setBounds(0, 0, 600, 400); // Define o tamanho e posição da janela

		// Inicializa a barra de menu e os menus
		mnBarra = new JMenuBar();
		mnEntrar = new JMenu("Entrar");
		mnEntrar.setMnemonic('E'); // Define o atalho do teclado
		mnSair = new JMenu("Sair");
		mnSair.setMnemonic('S'); // Define o atalho do teclado
		miSair = new JMenuItem("Sair");
		miInicio = new JMenuItem("Inicio");
		miCadastro = new JMenuItem("Cadastro");
		miLogin = new JMenuItem("Login");

		// Adiciona os menus e itens de menu à barra de menu
		setJMenuBar(mnBarra);
		mnBarra.add(mnEntrar);
		mnBarra.add(mnSair);
		mnEntrar.add(miCadastro);
		mnEntrar.add(miLogin);
		mnSair.add(miInicio); // Adiciona o item Início ao menu Sair
		mnSair.add(miSair);
	}

	// Método para definir os eventos dos componentes
	private void definirEventos() {
		// Define o evento de sair da aplicação
		miSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Sai da aplicação
			}
		});

		// Define o evento para abrir a tela de cadastro
		miCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarParaGUICadastro(); // Chama o método para trocar para a tela de cadastro
			}
		});

		// Define o evento para abrir a tela de login
		miLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarParaGUILogar(); // Chama o método para trocar para a tela de login
			}
		});

		// Define o evento para abrir a tela inicial, se um usuário estiver logado
		miInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usuarioLogado != null) {
					trocarParaGUIInicio(usuarioLogado); // Chama o método para trocar para a tela inicial
				}
			}
		});
	}

	// Método para trocar para a tela de cadastro
	private void trocarParaGUICadastro() {
		getContentPane().removeAll(); // Remove todos os componentes do content pane
		GUICriar guiCadastro = new GUICriar(usuarios); // Cria a tela de cadastro, passando a lista de usuários
		guiCadastro.setCadastroListener(new CadastroListener() {
			@Override
			public void onCadastro(Usuario usuario) {
				usuarioLogado = usuario; // Define o usuário logado
				trocarParaGUILogar(); // Troca para a tela de login após o cadastro
			}
		});
		getContentPane().add(guiCadastro); // Adiciona a tela de cadastro ao content pane
		revalidate(); // Revalida o layout
		repaint(); // Repinta a tela
	}

	// Método para trocar para a tela de login
	private void trocarParaGUILogar() {
		getContentPane().removeAll(); // Remove todos os componentes do content pane
		GUILogar guiLogin = new GUILogar(usuarios); // Cria a tela de login, passando a lista de usuários
		guiLogin.setLoginListener(new LoginListener() {
			@Override
			public void onLogin(Usuario usuario) {
				usuarioLogado = usuario; // Define o usuário logado
				trocarParaGUIInicio(usuario); // Troca para a tela inicial após o login
			}
		});
		getContentPane().add(guiLogin); // Adiciona a tela de login ao content pane
		revalidate(); // Revalida o layout
		repaint(); // Repinta a tela
	}

	// Método para trocar para a tela inicial
	private void trocarParaGUIInicio(Usuario usuario) {
		getContentPane().removeAll(); // Remove todos os componentes do content pane
		GUIInicio guiInicio = new GUIInicio(usuario); // Cria a tela inicial, passando o usuário logado
		getContentPane().add(guiInicio); // Adiciona a tela inicial ao content pane
		revalidate(); // Revalida o layout
		repaint(); // Repinta a tela
	}

	// Método para popular a lista de usuários com dados de exemplo
	private void popularUsuarios() {
		usuarios.add(new Usuario("user1", "123", "Usuário 1")); // Adiciona um usuário à lista
		usuarios.add(new Usuario("user2", "456", "Usuário 2")); // Adiciona um usuário à lista
		usuarios.add(new Usuario("user3", "789", "Usuário 3")); // Adiciona um usuário à lista
	}

	// Método main para iniciar a aplicação
	public static void main(String[] args) {
		GUIPrincipal frame = new GUIPrincipal(); // Cria a janela principal
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação de fechar a janela
		frame.setVisible(true); // Torna a janela visível
	}
}

// Interface CadastroListener para notificar quando um usuário for cadastrado
interface CadastroListener {
	void onCadastro(Usuario usuario);
}

// Interface LoginListener para notificar quando um usuário fizer login
interface LoginListener {
	void onLogin(Usuario usuario);
}
