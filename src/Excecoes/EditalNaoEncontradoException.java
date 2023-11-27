package Excecoes;

public class EditalNaoEncontradoException extends Exception {
	
	public String getMessage() {
		return "Edital não localizado";
	}
}
