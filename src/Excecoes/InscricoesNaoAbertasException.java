package Excecoes;

public class InscricoesNaoAbertasException extends Exception{
	
	public String getMessage() {
		return "Inscrições para esse edital não estão abertas";
	}
}
