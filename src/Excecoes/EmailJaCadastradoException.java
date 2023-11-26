package Excecoes;

public class EmailJaCadastradoException extends Exception {
	
	public String getMessage() {
		return "Email Já Cadastrado no Sistema!";
	}
}
