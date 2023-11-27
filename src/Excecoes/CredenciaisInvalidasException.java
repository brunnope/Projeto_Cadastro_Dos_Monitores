package Excecoes;

public class CredenciaisInvalidasException extends Exception {
	public String getMessage() {
		return "Email ou Senha Inválidos";
	}
}
