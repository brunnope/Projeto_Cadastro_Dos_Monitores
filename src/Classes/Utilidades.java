package Classes;

import org.apache.commons.mail.EmailException;

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
import Telas.TelaPadrao;

/**
 * A classe Utilidades Oferece métodos úteis para realizar operações comuns,
 * como cadastro de alunos, coordenadores, login, recuperação de senha, e
 * edição de informações de alunos e coordenadores.
 * 
 * @author Anderson da Silva
 * @version 1.0
 */
public class Utilidades {
	
	 /**
     * Cadastra um novo aluno com as informações fornecidas.
     * 
     * @param nome       Nome do aluno.
     * @param email1     Primeiro endereço de e-mail.
     * @param email2     Segundo endereço de e-mail para confirmação.
     * @param senha1     Primeira senha.
     * @param senha2     Segunda senha para confirmação.
     * @param matricula  Matrícula do aluno.
     * @param sex        Sexo do aluno (Masculino ou Feminino).
     * @return Retorna o objeto Aluno após o cadastro bem-sucedido.
     * @throws EmailInvalidoException        Se o e-mail fornecido não for válido.
     * @throws EmailDiferenteException       Se os endereços de e-mail fornecidos
     *                                       não coincidirem.
     * @throws SenhaMuitoPequenaException    Se a senha fornecida for muito curta.
     * @throws SenhaDiferenteException       Se as senhas fornecidas não coincidirem.
     * @throws CamposVaziosException         Se algum campo obrigatório estiver em
     *                                       branco.
     */

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
	/**
     * Cadastra um novo coordenador com as informações fornecidas.
     * Esse metódo só e chamado se não houver nenhum coordenador
     * No sistema.
     * 
     * @param nome   Nome do coordenador.
     * @param email1 Primeiro endereço de e-mail.
     * @param email2 Segundo endereço de e-mail para confirmação.
     * @param senha1 Primeira senha.
     * @param senha2 Segunda senha para confirmação.
     * @return Retorna o objeto Coordenador após o cadastro bem-sucedido.
     * @throws EmailDiferenteException    Se os endereços de e-mail fornecidos não
     *                                    coincidirem.
     * @throws SenhaMuitoPequenaException Se a senha fornecida for muito curta.
     * @throws SenhaDiferenteException    Se as senhas fornecidas não coincidirem.
     * @throws EmailInvalidoException     Se o e-mail fornecido não for válido.
     * @throws CamposVaziosException      Se algum campo obrigatório estiver em
     *                                    branco.
     */

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
	/**
     * Realiza o login de um usuário (aluno ou coordenador) com as credenciais
     * fornecidas.
     * 
     * @param email   Endereço de e-mail do usuário.
     * @param senha   Senha do usuário.
     * @param central Instância da CentralDeInformacoes contendo informações dos
     *                usuários.
     * @return Retorna um objeto Pessoa representando o usuário logado.
     * @throws CredenciaisInvalidasException Se as credenciais fornecidas 
     *		não existirem na central.
     */
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
	/**
     * Envia um e-mail de recuperação de senha para o endereço fornecido, usando a classe Mensageiro
     * como intermediaria.
     * 
     * @param central     CentralDeInformacoes contendo informações dos usuários.
     * @param email Endereço de e-mail para o qual enviar a recuperação de senha.
     * @throws EmailNaoEncontradoException Se o e-mail não estiver cadastrado no
     *                                      sistema.
     * @throws EmailException              Se ocorrer um erro ao enviar o e-mail.
     */
	public void recuperarSenhaPorEmail(CentralDeInformacoes central, String email) throws EmailNaoEncontradoException, EmailException {
		String senha = central.recuperarSenhaPeloEmail(email);
		Mensageiro.enviarEmail(email, "Recuperação de Senha" ,"Sua senha atual é: " + senha);
	}
	/**
     * Edita as informações de um aluno com base nos parâmetros fornecidos.
     * Os metódos verificarMatricula e emailExiste desconsideram a matricula 
     * e o email do aluno que está sendo editado.
     * 
     * @param central Instância da CentralDeInformacoes contendo informações dos
     *                usuários.
     * @param aluno   Aluno a ser editado.
     * @param nome    Novo nome do aluno.
     * @param email   Novo endereço de e-mail do aluno.
     * @param senha   Nova senha do aluno.
     * @param matricula Nova matrícula do aluno.
     * @param sex     Novo sexo do aluno.
     * @throws EmailInvalidoException       Se o novo endereço de e-mail não for
     *                                       válido.
     * @throws SenhaMuitoPequenaException    Se a nova senha fornecida for muito
     *                                       curta.
     * @throws CamposVaziosException         Se algum campo obrigatório estiver em
     *                                       branco.
     * @throws EmailJaCadastradoException   Se o novo endereço de e-mail já estiver
     *                                       cadastrado para outro usuário. 
     * @throws AlunoJaMatriculadoException Se a nova matrícula já estiver associada
     *                                       a outro aluno (apenas para coordenadores).
     */
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
		central.emailExiste(email, aluno);
		CentralDeInformacoes.validarEmail(email);
		aluno.setEmail(email);
		aluno.setNome(nome);
		aluno.setSenha(senha);
		aluno.setSexo(sexo);
		aluno.setMatricula(matricula);
	}	
	
	/**
     * Edita as informações de um coordenador com base nos parâmetros fornecidos.
     * 
     * @param central     Instância da CentralDeInformacoes contendo informações
     *                    dos usuários.
     * @param coordenador Coordenador a ser editado.
     * @param nome         Novo nome do coordenador.
     * @param email        Novo endereço de e-mail do coordenador.
     * @param senha        Nova senha do coordenador.
     * @throws EmailInvalidoException      Se o novo endereço de e-mail não for
     *                                      válido.
     * @throws SenhaMuitoPequenaException   Se a nova senha fornecida for muito
     *                                      curta.
     * @throws CamposVaziosException        Se algum campo obrigatório estiver em
     *                                      branco.
     * @throws EmailJaCadastradoException  Se o novo endereço de e-mail já estiver
     *                                      cadastrado para outro usuário.
     */
	public void editarCoordenador(CentralDeInformacoes central, Coordenador coordenador, String nome, String email, String senha) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException {
		nome.trim();
		email.trim();
		if(nome.isBlank() || email.isBlank() || senha.isBlank()) {
			throw new CamposVaziosException();
		}else if(senha.length() < 8){
			throw new SenhaMuitoPequenaException();
		}
		central.emailExiste(email, coordenador);
		CentralDeInformacoes.validarEmail(email);
		coordenador.setEmail(email);
		coordenador.setNome(nome);
		coordenador.setSenha(senha);
	}
	
	/**
     * Recupera o endereço de e-mail associado a uma determinada matrícula.
     * 
     * @param central   Instância da CentralDeInformacoes contendo informações dos
     *                  usuários.
     * @param matricula Matrícula do aluno.
     * @return Retorna o endereço de e-mail associado à matrícula ou null se a
     *         matrícula não for encontrada.
     */
	public String recuperarEmailPorMatricula(CentralDeInformacoes central, String matricula) {
		for(Aluno aluno: central.getTodosOsAlunos()) {
			if(aluno.getMatricula().equals(matricula)) {
				return aluno.getEmail();
			}
		}
		return null;
	}
}