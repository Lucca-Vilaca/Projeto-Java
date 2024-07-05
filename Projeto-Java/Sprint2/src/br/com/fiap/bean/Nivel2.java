package br.com.fiap.bean;

public class Nivel2 {

	// Atributos
	private double tempo;
	private double precisao;
	private int dificuldade;
	private int tentativas;

	// Construtor com e sem parâmetros
	public Nivel2(double tempo, double precisao, int dificuldade, int tentativas) {
		this.tempo = tempo;
		this.precisao = precisao;
		this.dificuldade = dificuldade;
		this.tentativas = tentativas;
	}

	public Nivel2() {
	}

	// Getters e Setters
	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public double getPrecisao() {
		return precisao;
	}

	public void setPrecisao(double precisao) {
		this.precisao = precisao;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public int getTentativas() {
		return tentativas;
	}

	public void setTentativas(int tentativas) {
		this.tentativas = tentativas;
	}

	// Método para calcular a nota
	public double calculaNota() {

		return ((precisao / tempo) * dificuldade) / tentativas;
	}
}
