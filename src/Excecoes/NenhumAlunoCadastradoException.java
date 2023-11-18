package Excecoes;

public class NenhumAlunoCadastradoException extends Exception {
	public String getMessage() {
		return "Nenhum aluno cadastrado no momento!";
	}
	
}
