package br.com.fiap.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.fiap.bean.Usuario;

// Define a classe GUIInicio que herda de JPanel e será usada para a tela inicial após o login
@SuppressWarnings("serial")
public class GUIInicio extends JPanel {

	// Declaração dos componentes da interface
	private JLabel lbBemVindo;
	private JButton btNivel1, btNivel2;
	private Usuario usuario; // Objeto usuário que armazena as informações do usuário logado

	// Construtor da classe que inicializa os componentes e define os eventos
	public GUIInicio(Usuario usuario) {
		this.usuario = usuario;
		inicializarComponentes();
		definirEventos();
	}

	// Método para inicializar os componentes da interface gráfica
	private void inicializarComponentes() {
		setLayout(null); // Define o layout como nulo para posicionar os componentes manualmente
		setBackground(Color.GRAY); // Define a cor de fundo do painel

		// Inicializa o rótulo de boas-vindas e define suas propriedades
		lbBemVindo = new JLabel("Bem-vindo, " + usuario.getUsuario(), JLabel.CENTER);
		lbBemVindo.setForeground(Color.WHITE);
		lbBemVindo.setFont(new Font("Arial", Font.BOLD, 16));

		// Inicializa os botões de nível 1 e nível 2
		btNivel1 = new JButton("Calcular nota - Nível 1");
		btNivel2 = new JButton("Calcular nota - Nível 2");

		// Adiciona os componentes ao painel
		add(lbBemVindo);
		add(btNivel1);
		add(btNivel2);

		// Define as posições e tamanhos dos componentes
		lbBemVindo.setBounds(50, 30, 300, 30);
		btNivel1.setBounds(100, 80, 200, 30);
		btNivel2.setBounds(100, 130, 200, 30);
	}

	// Método para definir os eventos dos componentes
	private void definirEventos() {
		btNivel1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtém a janela principal e troca o conteúdo para a interface do Nível 1
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GUIInicio.this);
				topFrame.getContentPane().removeAll();
				topFrame.getContentPane().add(new GUINivel1());
				topFrame.revalidate();
				topFrame.repaint();
			}
		});

		btNivel2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtém a janela principal e troca o conteúdo para a interface do Nível 2
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GUIInicio.this);
				topFrame.getContentPane().removeAll();
				topFrame.getContentPane().add(new GUINivel2());
				topFrame.revalidate();
				topFrame.repaint();
			}
		});
	}
}
