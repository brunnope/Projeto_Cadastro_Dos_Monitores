package Excecoes;

public class InscricoesFinalizadaException extends Exception{
	
	private String erro;
	
	public InscricoesFinalizadaException(String erro) {
		this.erro = erro;
	}
	
	public InscricoesFinalizadaException() {
		
	}
	
	public String getMessage() {
		return "Período de inscrição se encontra fechado";
	}
	
	public String getErroModificado() {
		return erro;
	}
}
