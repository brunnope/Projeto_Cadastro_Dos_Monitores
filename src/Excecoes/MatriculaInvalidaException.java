package Excecoes;

public class MatriculaInvalidaException extends Exception {
	
	public String getMessage() {
		return "A Matricula Deve Ter 12 Caracteres!";
	}
}