package Classes;

public class Pessoa {
	private String nome;
	private String email;
	private String senha;
	private Sexo sexo;
	
	public Pessoa(String nome, Sexo sexo, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.sexo = sexo;
		this.senha = senha;
	}
	public Pessoa(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	public Pessoa(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	public Pessoa() {
		
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
}
