package br.com.fiap.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.fiap.bean.Nivel1;

// Define a classe GUINivel1 que herda de JPanel e será usada para a interface de cálculo de nota do Nível 1
@SuppressWarnings("serial")
public class GUINivel1 extends JPanel {

	// Declaração dos componentes da interface
	private JLabel lbTitulo, lbTempo, lbPrecisao, lbDificuldade;
	private JTextField tfTempo, tfPrecisao, tfDificuldade;
	private JButton btCalcular;

	// Construtor da classe que inicializa os componentes e define os eventos
	public GUINivel1() {
		inicializarComponentes();
		definirEventos();
	}

	// Método para inicializar os componentes da interface gráfica
	private void inicializarComponentes() {
		setLayout(null); // Define o layout como nulo para posicionar os componentes manualmente
		setBackground(Color.GRAY); // Define a cor de fundo do painel

		// Inicializa o rótulo de título e define suas propriedades
		lbTitulo = new JLabel("Nível 1 - Cálculo de Nota", JLabel.CENTER);
		lbTitulo.setForeground(Color.WHITE);
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 18));

		// Inicializa os rótulos de tempo, precisão e dificuldade
		lbTempo = new JLabel("Tempo (minutos):", JLabel.LEFT);
		lbTempo.setForeground(Color.WHITE);

		lbPrecisao = new JLabel("Precisão (%):", JLabel.LEFT);
		lbPrecisao.setForeground(Color.WHITE);

		lbDificuldade = new JLabel("Dificuldade (1-10):", JLabel.LEFT);
		lbDificuldade.setForeground(Color.WHITE);

		// Inicializa os campos de texto para tempo, precisão e dificuldade
		tfTempo = new JTextField();
		tfPrecisao = new JTextField();
		tfDificuldade = new JTextField();

		// Inicializa o botão de calcular nota
		btCalcular = new JButton("Calcular Nota");

		// Adiciona os componentes ao painel
		add(lbTitulo);
		add(lbTempo);
		add(lbPrecisao);
		add(lbDificuldade);
		add(tfTempo);
		add(tfPrecisao);
		add(tfDificuldade);
		add(btCalcular);

		// Define as posições e tamanhos dos componentes
		lbTitulo.setBounds(50, 30, 300, 30);
		lbTempo.setBounds(50, 80, 150, 25);
		lbPrecisao.setBounds(50, 120, 150, 25);
		lbDificuldade.setBounds(50, 160, 150, 25);
		tfTempo.setBounds(200, 80, 150, 25);
		tfPrecisao.setBounds(200, 120, 150, 25);
		tfDificuldade.setBounds(200, 160, 150, 25);
		btCalcular.setBounds(150, 200, 150, 30);
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

					// Validar se os campos foram preenchidos corretamente
					if (tempo <= 0 || precisao < 0 || precisao > 100 || dificuldade < 1 || dificuldade > 10) {
						throw new IllegalArgumentException("Valores inválidos. Verifique os campos.");
					}

					// Criar objeto Nivel1 com os valores informados
					Nivel1 nivel1 = new Nivel1(tempo, precisao, dificuldade);

					// Calcular a nota
					double nota = nivel1.calculaNota();

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
}
