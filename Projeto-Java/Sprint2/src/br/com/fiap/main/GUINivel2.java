package br.com.fiap.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.fiap.bean.Nivel2;

// Define a classe GUINivel2 que herda de JPanel e será usada para a interface de cálculo de nota do Nível 2
@SuppressWarnings("serial")
public class GUINivel2 extends JPanel {

	// Declaração dos componentes da interface
	private JLabel lbTitulo, lbTempo, lbPrecisao, lbDificuldade, lbTentativas;
	private JTextField tfTempo, tfPrecisao, tfDificuldade, tfTentativas;
	private JButton btCalcular;

	// Construtor da classe que inicializa os componentes e define os eventos
	public GUINivel2() {
		inicializarComponentes();
		definirEventos();
	}

	// Método para inicializar os componentes da interface gráfica
	private void inicializarComponentes() {
		setLayout(null); // Define o layout como nulo para posicionar os componentes manualmente
		setBackground(Color.GRAY); // Define a cor de fundo do painel

		// Inicializa o rótulo de título e define suas propriedades
		lbTitulo = new JLabel("Nível 2 - Cálculo de Nota", JLabel.CENTER);
		lbTitulo.setForeground(Color.WHITE);
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 18));

		// Inicializa os rótulos de tempo, precisão, dificuldade e tentativas
		lbTempo = new JLabel("Tempo (minutos):", JLabel.LEFT);
		lbTempo.setForeground(Color.WHITE);

		lbPrecisao = new JLabel("Precisão (%):", JLabel.LEFT);
		lbPrecisao.setForeground(Color.WHITE);

		lbDificuldade = new JLabel("Dificuldade (1-10):", JLabel.LEFT);
		lbDificuldade.setForeground(Color.WHITE);

		lbTentativas = new JLabel("Tentativas:", JLabel.LEFT);
		lbTentativas.setForeground(Color.WHITE);

		// Inicializa os campos de texto para tempo, precisão, dificuldade e tentativas
		tfTempo = new JTextField();
		tfPrecisao = new JTextField();
		tfDificuldade = new JTextField();
		tfTentativas = new JTextField();

		// Inicializa o botão de calcular nota
		btCalcular = new JButton("Calcular Nota");

		// Adiciona os componentes ao painel
		add(lbTitulo);
		add(lbTempo);
		add(lbPrecisao);
		add(lbDificuldade);
		add(lbTentativas);
		add(tfTempo);
		add(tfPrecisao);
		add(tfDificuldade);
		add(tfTentativas);
		add(btCalcular);

		// Define as posições e tamanhos dos componentes
		lbTitulo.setBounds(50, 30, 300, 30);
		lbTempo.setBounds(50, 80, 150, 25);
		lbPrecisao.setBounds(50, 120, 150, 25);
		lbDificuldade.setBounds(50, 160, 150, 25);
		lbTentativas.setBounds(50, 200, 150, 25);
		tfTempo.setBounds(200, 80, 150, 25);
		tfPrecisao.setBounds(200, 120, 150, 25);
		tfDificuldade.setBounds(200, 160, 150, 25);
		tfTentativas.setBounds(200, 200, 150, 25);
		btCalcular.setBounds(150, 240, 150, 30);
	}

	// Método para definir os eventos dos componentes
	private void definirEventos() {
		btCalcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Obter valores dos campos de texto
					double tempo = Double.parseDouble(tfTempo.getText());
					double precisao = Double.parseDouble(tfPrecisao.getText());
					int dificuldade = Integer.parseInt(tfDificuldade.getText());
					int tentativas = Integer.parseInt(tfTentativas.getText());

					// Validar se os campos foram preenchidos corretamente
					if (tempo <= 0 || precisao < 0 || precisao > 100 || dificuldade < 1 || dificuldade > 10
							|| tentativas <= 0) {
						throw new IllegalArgumentException("Valores inválidos. Verifique os campos.");
					}

					// Criar objeto Nivel2 com os valores informados
					Nivel2 nivel2 = new Nivel2(tempo, precisao, dificuldade, tentativas);

					// Calcular a nota
					double nota = nivel2.calculaNota();

					// Exibir resultado
					JOptionPane.showMessageDialog(null, "Sua nota é: " + nota, "Nota Calculada",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException ex) {
					// Mensagem de erro para valores numéricos inválidos
					JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.",
							"Erro de Formato", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException ex) {
					// Mensagem de erro para valores inválidos nos campos
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// Método main para testar a interface gráfica
	public static void main(String[] args) {
		JFrame frame = new JFrame("Exemplo GUI Nível 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar a janela
		frame.setSize(400, 320); // Define o tamanho da janela
		frame.getContentPane().setBackground(Color.BLACK); // Define a cor de fundo do content pane
		frame.setLocationRelativeTo(null); // Centraliza a janela na tela

		GUINivel2 panel = new GUINivel2(); // Cria uma instância de GUINivel2
		frame.getContentPane().add(panel); // Adiciona o painel ao content pane da janela

		frame.setVisible(true); // Torna a janela visível
	}
}
