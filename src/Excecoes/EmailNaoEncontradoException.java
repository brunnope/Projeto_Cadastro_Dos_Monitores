package Excecoes;

public class EmailNaoEncontradoException extends Exception{
	public String getMessage() {
		return "Email Não Encontrado!";
	}
}
