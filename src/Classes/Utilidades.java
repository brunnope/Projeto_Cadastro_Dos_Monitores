package Classes;

import Excecoes.CamposVaziosException;
import Excecoes.CredenciaisInvalidasException;
import Excecoes.EmailDiferenteException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailNaoEncontradoException;
import Excecoes.NenhumAlunoCadastradoException;
import Excecoes.SenhaDiferenteException;
import Excecoes.SenhaMuitoPequenaException;
import Persistencia.CentralDeInformacoes;
import Telas.TelaLogin;

public class Utilidades {
	
	public Aluno cadastrarAluno(String nome, String email1, String email2, String senha1, String senha2, String matricula, String sex) 
			throws EmailInvalidoException,EmailDiferenteException, SenhaMuitoPequenaException, SenhaDiferenteException, CamposVaziosException {
		nome.trim();
		email1.trim();
		email2.trim();
		sex.trim();
		matricula.trim();
		Sexo sexo = Sexo.valueOf(sex.toUpperCase());
		if(nome.isBlank() || email1.isBlank() || email2.isBlank() || senha1.isBlank() || senha2.isBlank() || matricula.isBlank()) {
			throw new CamposVaziosException();
		}else if (!email1.equals(email2)) {
			throw new EmailDiferenteException();
		}else if(senha1.length() < 8){
			throw new SenhaMuitoPequenaException();
		}else if (!senha1.equals(senha2)) {
			throw new SenhaDiferenteException();
		} else {
			CentralDeInformacoes.validarEmail(email1);
			Aluno a = new Aluno(nome, sexo, matricula, email1, senha1);
			return a;
		}
	}
	
	
	public Coordenador cadastrarCoordenador(String nome, String email1, String email2, String senha1, String senha2) throws EmailDiferenteException,
	SenhaDiferenteException, EmailInvalidoException, SenhaMuitoPequenaException {
		CentralDeInformacoes.validarEmail(email1);
			if (!email1.equals(email2)) {
				throw new EmailDiferenteException();
			}else if(senha1.length() < 8){
				throw new SenhaMuitoPequenaException();
			}else if (!senha1.equals(senha2)) {
				throw new SenhaDiferenteException();
			}else {
				Coordenador c = new Coordenador(nome, email1, senha1);
				return c;
				}
		}
	
	public Pessoa login(String email, String senha, CentralDeInformacoes central) throws NenhumAlunoCadastradoException, CredenciaisInvalidasException {
		Coordenador c = central.getCoordenador();
			if (c.getEmail().equals(email) && c.getSenha().equals(senha)) {
				return c;
			}
		
		for(Aluno a: central.getTodosOsAlunos()) {
			if (a.getEmail().equals(email) && a.getSenha().equals(senha)) {
				return a;
			}
		}throw new CredenciaisInvalidasException();
	}
	
	public void recuperarSenhaPorEmail(CentralDeInformacoes c, String email) throws EmailNaoEncontradoException {
		CentralDeInformacoes central = c;
		String senha = central.recuperarSenhaPeloEmail(email);
		Mensageiro.enviarEmail(email, "Sua senha atual é: " + senha);
	}
	
	public static void main(String[] args) {
		TelaLogin t = new TelaLogin();
	}
}
