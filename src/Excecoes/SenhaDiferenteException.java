package Excecoes;

public class SenhaDiferenteException extends Exception {
	
	public String getMessage() {
		return "As Senhas Devem Ser Iguais!";
	}
}
