package Classes;
import java.util.ArrayList;
import java.util.HashMap;
public class Disciplina {
	private String nome;
	private int quantDeVagasRemuneradas;
	private int quantDeVagasVoluntarias;
	private HashMap<Aluno, Inscricao> inscricoes = new HashMap<>();
	
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
	
	public HashMap<Aluno, Inscricao> getInscricoes() {
		if (inscricoes == null) {
			inscricoes = new HashMap<>();		}
		return inscricoes;
	}
	public void setInscricoes(HashMap<Aluno, Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}
	
	
}