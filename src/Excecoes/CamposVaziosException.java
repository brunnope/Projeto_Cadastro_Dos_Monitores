package Excecoes;

public class CamposVaziosException extends Exception {
	
	public String getMessage() {
		return "Preencha os Campos Vazios!";
	}
}