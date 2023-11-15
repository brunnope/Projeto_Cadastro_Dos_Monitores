package Excecoes;

public class AlunoNaoEncontradoException extends Exception {
	
	public String getMessage() {
		return "Aluno informado não encontrado";
	}
}
