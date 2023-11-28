package Excecoes;

public class EmailInvalidoException extends Exception {
	
	public String getMessage() {
		return "Digite Um Email Válido!";
	}
}
