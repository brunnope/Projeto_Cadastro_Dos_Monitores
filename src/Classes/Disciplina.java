package Classes;

import java.util.ArrayList;

public class Disciplina {
	private String nome;
	private int quantVagas;
	private ArrayList<Aluno> alunosInscritos = new ArrayList<Aluno>();
	private int quantDeVagasRemuneradas;
	private int quantDeVagasVoluntarias;
	
	public Disciplina(String nome, int quantRemuneradas, int quantVoluntarias) {
		this.nome = nome;
		quantDeVagasRemuneradas = quantRemuneradas;
		quantDeVagasVoluntarias = quantVoluntarias;
	}
	
	public int getQuantDeVagasRemuneradas() {
		return quantDeVagasRemuneradas;
	}
	
	public void setQuantDeVagasRemuneradas(int quantDeVagasRemuneradas) {
		this.quantDeVagasRemuneradas = quantDeVagasRemuneradas;
	}
	
	public int getQuantDeVagasVoluntarias() {
		return quantDeVagasVoluntarias;
	}
	
	public void setQuantDeVagasVoluntarias(int quantDeVagasVoluntarias) {
		this.quantDeVagasVoluntarias = quantDeVagasVoluntarias;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantVagas() {
		return quantVagas;
	}
	public void setQuantVagas(int quantVagas) {
		this.quantVagas = quantVagas;
	}
	public ArrayList<Aluno> getAlunosInscritos() {
		return alunosInscritos;
	}
	public void setAlunosInscritos(ArrayList<Aluno> alunosInscritos) {
		this.alunosInscritos = alunosInscritos;
	}
}
