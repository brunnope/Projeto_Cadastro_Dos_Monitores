package Excecoes;

public class DisciplinaJaCadastradaException extends Exception{
	public String getMessage() {
		return "Disciplina já cadastrada no edital!";
	}
}