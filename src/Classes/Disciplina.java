package Classes;
import java.util.ArrayList;

public class Disciplina {
	private String nome;
	private int quantVagas;
	private ArrayList<Aluno> alunosInscritos = new ArrayList<Aluno>();
	
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
