package Classes;

import Excecoes.CredenciaisInvalidasException;
import Excecoes.EmailDiferenteException;
import Excecoes.EmailInvalidoException;
import Excecoes.NenhumAlunoCadastradoException;
import Excecoes.SenhaDiferenteException;
import Excecoes.SenhaMuitoPequenaException;
import Persistencia.CentralDeInformacoes;
import Telas.TelaLogin;

public class Utilidades {
	public Coordenador cadastrarCoordenador(String n, String e1, String e2, String s1, String s2) throws EmailDiferenteException,
	SenhaDiferenteException, EmailInvalidoException, SenhaMuitoPequenaException {
		CentralDeInformacoes.validarEmail(e1);
			if (!e1.equals(e2)) {
				throw new EmailDiferenteException();
			}else if(s1.length() < 8){
				throw new SenhaMuitoPequenaException();
			}else if (!s1.equals(s2)) {
				throw new SenhaDiferenteException();
			}else {
				Coordenador c = new Coordenador(n, e1, s1);
				return c;
				}
		}
	
	public Pessoa login(String email, String senha, CentralDeInformacoes central) throws NenhumAlunoCadastradoException, CredenciaisInvalidasException {
		for (Coordenador c: central.getCoordenador()) {
			if (c.getEmail().equals(email) && c.getSenha().equals(senha)) {
				return c;
			}
		}
		for(Aluno a: central.getTodosOsAlunos()) {
			if (a.getEmail().equals(email) && a.getSenha().equals(senha)) {
				return a;
			}
		}throw new CredenciaisInvalidasException();
	}
	public static void main(String[] args) {
		TelaLogin t = new TelaLogin();
	}
}
