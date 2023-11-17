package Excecoes;

public class NenhumEditalCadastradoException extends Exception {
	
	public String getMessage() {
		return "Nenhum edital cadastrado atualmente";
	}
}
