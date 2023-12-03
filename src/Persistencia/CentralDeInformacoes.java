package Persistencia;

import java.util.ArrayList;

import Classes.Aluno;
import Classes.Coordenador;
import Classes.EditalDeMonitoria;
import Classes.Pessoa;
import Excecoes.AlunoJaMatriculadoException;
import Excecoes.AlunoNaoEncontradoException;
import Excecoes.EditalInvalidoException;
import Excecoes.EditalNaoEncontradoException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailJaCadastradoException;
import Excecoes.EmailNaoEncontradoException;
import Excecoes.NumeroDoEditalJaExistenteException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A classe `CentralDeInformacoes` é responsável por gerenciar e manter as informações do sistema,
 * como coordenador, alunos, editais, etc.
 * @author BrunnoPE
 * @version 1.0
 */
public class CentralDeInformacoes {
	
	/**
     * Coordenador unico do sistema.
     */
	private Coordenador coordenador = null;
	/**
     * Lista de todos os alunos cadastrados no sistema.
     */
	private ArrayList<Aluno> todosOsAlunos = new ArrayList<Aluno>();
	/**
     * Lista de todos os editais cadastrados no sistema.
     */
	private ArrayList<EditalDeMonitoria> todosOsEditais = new ArrayList<EditalDeMonitoria>();
	 /**
     * Verifica se um edital já existe no sistema com base no número do edital.
     *
     * @param edital Edital a ser verificado.
     * @return Verdadeiro se o edital não existe, falso se já existe.
     * @throws NumeroDoEditalJaExistenteException Se o número do edital já existe.
     */
	public boolean editalExiste(EditalDeMonitoria edital) throws NumeroDoEditalJaExistenteException {
		for(EditalDeMonitoria e:todosOsEditais) {
			if(e.getNumeroEdital() == edital.getNumeroEdital()) {
				throw new NumeroDoEditalJaExistenteException();				
			}
		}
		return true;
	}
	 /**
     * Valida um endereço de e-mail usando expressão regular.
     *
     * @param login E-mail a ser validado.
     * @return Verdadeiro se o e-mail é válido.
     * @throws EmailInvalidoException Se o e-mail não é válido.
     */
	public static boolean validarEmail(String login) throws EmailInvalidoException {
		String padraoEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(padraoEmail, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(login);
		if (!matcher.matches()) {
			throw new EmailInvalidoException();
		}
		return matcher.matches();
	}
	 /**
     * Verifica se um e-mail já está cadastrado para um aluno ou coordenador.
     *
     * @param email E-mail a ser verificado.
     * @throws EmailJaCadastradoException Se o e-mail já está cadastrado.
     */
	public boolean emailExiste(String email) throws EmailJaCadastradoException {
		for (Aluno aluno : todosOsAlunos) {
			if (aluno.getEmail().equalsIgnoreCase(email)) {
				throw new EmailJaCadastradoException();
			}
		}
		if (coordenador.getEmail().equalsIgnoreCase(email)) {
			throw new EmailJaCadastradoException();
		}return false;
	}
	 /**
     * Sobrecarga do método `emailExiste` para verificar se um e-mail já está cadastrado para
     * um aluno ou coordenador específico.
     *
     * @param email  E-mail a ser verificado.
     * @param pessoa Pessoa (aluno ou coordenador) para a qual o e-mail não deve ser verificado.
     * @throws EmailJaCadastradoException Se o e-mail já está cadastrado.
     */
	public boolean emailExiste(String email,Pessoa pessoa) throws EmailJaCadastradoException {
		for (Aluno aluno : todosOsAlunos) {
			if (aluno != pessoa) {
				if (aluno.getEmail().equalsIgnoreCase(email)) {
					throw new EmailJaCadastradoException();
				}
			}
		}
		if (coordenador != pessoa) {
			if (coordenador.getEmail().equalsIgnoreCase(email)) {
				throw new EmailJaCadastradoException();
			}
		}
		return false;
	}
	/**
     * Adiciona um coordenador ao sistema.
     *
     * @param c Coordenador a ser adicionado.
     * @return Verdadeiro se o coordenador foi adicionado com sucesso, falso se o
     *  coordenador já existir no sistema
     */
	public boolean adicionarCoordenador(Coordenador c) {
		if (coordenador == null) {
			coordenador = c;
			return true;
		} else {
			return false;

		}

	}
	/**
     * Adiciona um edital de monitoria ao sistema.
     *
     * @param edital Edital a ser adicionado.
     * @return Verdadeiro se o edital foi adicionado com sucesso.
     * @throws EditalInvalidoException Se o edital já existe.
     */
	public boolean adicionarEdital(EditalDeMonitoria edital) throws EditalInvalidoException {
		if (!(todosOsEditais.size() == 0)){
			for (EditalDeMonitoria editais: todosOsEditais) {
				if (editais.getId() == edital.getId()) {
					throw new EditalInvalidoException();
				}
			}
		}
		todosOsEditais.add(edital);
		return true;
	}
	/**
     * Recupera um edital de monitoria pelo seu ID.
     *
     * @param id ID do edital a ser recuperado.
     * @return Edital de monitoria correspondente ao ID.
     * @throws EditalNaoEncontradoException Se o edital não foi encontrado.
     */
	public EditalDeMonitoria recuperarEditalPeloId(long id) throws EditalNaoEncontradoException{
		for (EditalDeMonitoria edital: todosOsEditais) {
			if (edital.getId() == id) {
				return edital;
			}
		}
		throw new EditalNaoEncontradoException();
	}
	 /**
     * Verifica se uma matrícula já está cadastrada para um aluno.
     *
     * @param matricula Matrícula a ser verificada.
     * @throws AlunoJaMatriculadoException Se a matrícula já está cadastrada.
     */
	public boolean verificarMatricula(String matricula) throws AlunoJaMatriculadoException {
		for (Aluno aluno: todosOsAlunos) {
			if (aluno.getMatricula().equals(matricula)){
				throw new AlunoJaMatriculadoException();
			}

		}return false;
	}
	/**
     * Sobrecarga do método `verificarMatricula` para verificar se uma matrícula já está cadastrada
     * para um aluno específico.
     *
     * @param a        Aluno para o qual a matrícula não deve ser verificada.
     * @param matricula Matrícula a ser verificada.
     * @throws AlunoJaMatriculadoException Se a matrícula já está cadastrada.
     */
	public boolean verificarMatricula(Aluno a, String matricula) throws AlunoJaMatriculadoException {
		for (Aluno aluno: todosOsAlunos) {
			if (aluno != a) {
				if (aluno.getMatricula().equals(matricula)){
					throw new AlunoJaMatriculadoException();
				}
			}

		}return false;
	}
	 /**
     * Adiciona um aluno ao sistema.
     *
     * @param a Aluno a ser adicionado.
     * @return Verdadeiro se o aluno foi adicionado com sucesso.
     * @throws AlunoJaMatriculadoException  Se a matrícula já está cadastrada.
     * @throws EmailJaCadastradoException  Se o e-mail já está cadastrado.
     */
	public boolean adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		if (!todosOsAlunos.isEmpty()) {
			verificarMatricula(a.getMatricula());
			emailExiste(a.getEmail()); 
		}
		todosOsAlunos.add(a);
		return true;
	}
	/**
     * Recupera um aluno pelo número da matrícula.
     *
     * @param numMatricula Número da matrícula do aluno a ser recuperado.
     * @return Aluno correspondente à matrícula.
     * @throws AlunoNaoEncontradoException Se o aluno não foi encontrado.
     */
	public Aluno recuperarAlunoPorMatricula(String numMatricula) throws AlunoNaoEncontradoException {
		for (Aluno a: todosOsAlunos) {
			if (a.getMatricula().equals(numMatricula)) {
				return a;
			}
		}
		throw new AlunoNaoEncontradoException();
	}
	 /**
     * Recupera a senha de um usuário pelo seu e-mail.
     *
     * @param email E-mail do usuário.
     * @return Senha correspondente ao e-mail.
     * @throws EmailNaoEncontradoException Se o e-mail não foi encontrado.
     */
	public String recuperarSenhaPeloEmail (String email) throws EmailNaoEncontradoException {
		if (coordenador.getEmail().equalsIgnoreCase(email)) {
			return coordenador.getSenha();
		} else {
			for (Aluno aluno: todosOsAlunos) {
				if (aluno.getEmail().equalsIgnoreCase(email)) {
					return aluno.getSenha();
				}
			}
		}
		throw new EmailNaoEncontradoException();
	}
	/**
     * Obtém a lista de todos os alunos cadastrados no sistema.
     *
     * @return Lista de todos os alunos.
     */
	public ArrayList<Aluno> getTodosOsAlunos() {
		return todosOsAlunos;
	}
	/**
     * Define a lista de todos os alunos cadastrados no sistema.
     *
     * @param alunos Lista de alunos a ser definida.
     */
	public void setTodosOsAlunos(ArrayList<Aluno> alunos) {
		todosOsAlunos = alunos;
	}
	 /**
     * Obtém a lista de todos os editais de monitoria cadastrados no sistema.
     *
     * @return Lista de todos os editais de monitoria.
     */
	public ArrayList<EditalDeMonitoria> getTodosOsEditais() {
		if (todosOsEditais == null) {
			return new ArrayList<EditalDeMonitoria>();
		} else {
			return todosOsEditais;
		}
	}
	 /**
     * Define a lista de todos os editais de monitoria cadastrados no sistema.
     *
     * @param todosOsEditais Lista de editais a ser definida.
     */
	public void setTodosOsEditais(ArrayList<EditalDeMonitoria> todosOsEditais) {
		this.todosOsEditais = todosOsEditais;
	}
	 /**
     * Obtém o coordenador responsável pelo sistema.
     *
     * @return Coordenador responsável pelo sistema.
     */
	public Coordenador getCoordenador() {
		return coordenador;
	}
}