package Excecoes;

public class InscricoesFinalizadaException extends Exception{
	
	private String erro;
	
	public InscricoesFinalizadaException(String erro) {
		this.erro = erro;
	}
	
	public InscricoesFinalizadaException() {
		
	}
	
	public String getMessage() {
		return "Período de inscrições encerradas";
	}
	
	public String getErroModificado() {
		return erro;
	}
}
