package Classes;
public class Aluno extends Pessoa{
	private String matricula;
	
	public Aluno(String nome, Sexo sexo, String matricula, String email, String senha) {
		super(nome, sexo, email, senha);
		this.matricula = matricula;
		
	}
	
	public Aluno(String nome, String matricula, String email, String senha) {
		super(nome, email, senha);
		this.matricula = matricula;
	}
	
	public Aluno(){
		
	}
	
	public String toString() {
		return "Nome: " + getNome() + "\n" +
			"Sexo: " + getSexo().toString().toLowerCase() + "\n" +
			"Matricula: " + getMatricula() + "\n" +
			"Email: " + getEmail() + "\n";
		}

	public String getMatricula() {
		return matricula;
	}
}