package Excecoes;

public class AlunoJaInscritoException extends Exception{
	
	public String getMessage() {
		return "Aluno encontrado já inscrito no edital.";
	}
}
