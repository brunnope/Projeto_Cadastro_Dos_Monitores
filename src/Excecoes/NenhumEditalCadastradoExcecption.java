package Excecoes;

public class NenhumEditalCadastradoExcecption extends Exception {
	public String getMessage() {
		return "Nenhum Edital Cadastrado no Momento!";
	}
}
