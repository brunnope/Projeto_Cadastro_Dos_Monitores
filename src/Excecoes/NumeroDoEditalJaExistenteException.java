package Excecoes;

public class NumeroDoEditalJaExistenteException extends Exception{

	public String getMessage() {
		return "Edital com o mesmo número já existente";
	}
}
