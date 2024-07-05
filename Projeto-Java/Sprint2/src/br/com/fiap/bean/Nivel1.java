package br.com.fiap.bean;

public class Nivel1 {

	// Atributos
	private double tempo;
	private double precisao;
	private int dificuldade;

	// Construtor com e sem Parâmetros
	public Nivel1(double tempo, double precisao, int dificuldade) {
		this.tempo = tempo;
		this.precisao = precisao;
		this.dificuldade = dificuldade;
	}

	public Nivel1() {
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

	// Método para calcular a nota
	public double calculaNota() {

		return (precisao / tempo) * dificuldade;
	}
}
