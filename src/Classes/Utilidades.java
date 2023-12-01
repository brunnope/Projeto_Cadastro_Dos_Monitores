package Classes;

import Excecoes.AlunoJaMatriculadoException;
import Excecoes.CamposVaziosException;
import Excecoes.CredenciaisInvalidasException;
import Excecoes.EmailDiferenteException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailJaCadastradoException;
import Excecoes.EmailNaoEncontradoException;
import Excecoes.SenhaDiferenteException;
import Excecoes.SenhaMuitoPequenaException;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;
import Telas.TelaLogin;
import Telas.TelaPadrao;

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
		} else{
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

	public Pessoa login(String email, String senha, CentralDeInformacoes central) throws CredenciaisInvalidasException {
		Coordenador c = central.getCoordenador();
		if (c.getEmail().equalsIgnoreCase(email) && c.getSenha().equals(senha)) {
			return c;
		}

		for(Aluno a: central.getTodosOsAlunos()) {
			if (a.getEmail().equalsIgnoreCase(email) && a.getSenha().equals(senha)) {
				return a;
			}
		}throw new CredenciaisInvalidasException();
	}

	public void recuperarSenhaPorEmail(CentralDeInformacoes c, String email) throws EmailNaoEncontradoException {
		CentralDeInformacoes central = c;
		String senha = central.recuperarSenhaPeloEmail(email);
		Mensageiro.enviarEmail(email, "Sua senha atual é: " + senha);
	}
	public void editarAluno(CentralDeInformacoes central, Aluno aluno, String nome, String email, String senha, String matricula, String sex) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException {
		nome.trim();
		email.trim();
		sex.trim();
		matricula.trim();
		Sexo sexo = Sexo.valueOf(sex.toUpperCase());
		if(nome.isBlank() || email.isBlank() || senha.isBlank() || matricula.isBlank()) {
			throw new CamposVaziosException();
		}else if(senha.length() < 8){
			throw new SenhaMuitoPequenaException();
		}
		if (TelaPadrao.getUsuario() instanceof Coordenador) {
		central.verificarMatricula(aluno, matricula);
		}
		central.emailExiste(email);
		CentralDeInformacoes.validarEmail(email);
		aluno.setEmail(email);
		aluno.setNome(nome);
		aluno.setSenha(senha);
		aluno.setSexo(sexo);
		aluno.setMatricula(matricula);
	}	
}

