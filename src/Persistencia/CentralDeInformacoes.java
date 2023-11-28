package Persistencia;

import java.util.ArrayList;

import Classes.Aluno;
import Classes.Coordenador;
import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Excecoes.AlunoJaMatriculadoException;
import Excecoes.AlunoNaoEncontradoException;
import Excecoes.EditalInvalidoException;
import Excecoes.EditalNaoEncontradoException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailJaCadastradoException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class CentralDeInformacoes {
	private Coordenador coordenador = null;
	private ArrayList<Aluno> todosOsAlunos = new ArrayList<Aluno>();
	private ArrayList<EditalDeMonitoria> todosOsEditais = new ArrayList<EditalDeMonitoria>();



	public static boolean validarEmail(String login) throws EmailInvalidoException {
		String padraoEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(padraoEmail, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(login);
		if (!matcher.matches()) {
			throw new EmailInvalidoException();
		}
		return matcher.matches();
	}
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


	public boolean adicionarCoordenador(Coordenador c) {
		if (coordenador == null) {
			coordenador = c;
			return true;
		} else {
			return false;

		}

	}

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

	public EditalDeMonitoria recuperarEditalPeloId(long id) throws EditalNaoEncontradoException{
		for (EditalDeMonitoria edital: todosOsEditais) {
			if (edital.getId() == id) {
				return edital;
			}
		}
		throw new EditalNaoEncontradoException();
	}

	public ArrayList<Disciplina> recuperarInscriçõesDeUmAlunoEmUmEdital(String matricula, long id) throws AlunoNaoEncontradoException, EditalNaoEncontradoException{
		boolean existeEdital = false;
		boolean existeAluno = false;
		ArrayList<Disciplina> disciplinasAluno = new ArrayList<Disciplina>();

		for (Aluno aluno: todosOsAlunos) {
			if(aluno.getMatricula().equals(matricula)) {
				existeAluno = true;
				break;
			}
		}

		if (existeAluno) {
			for (EditalDeMonitoria edital: todosOsEditais) {
				if (edital.getId() == id) {
					for(Disciplina disciplina: edital.getDisciplinas()) {
						for(Aluno aluno: disciplina.getAlunosInscritos()) {
							if (aluno.getMatricula().equals(matricula)) {
								disciplinasAluno.add(disciplina);
								break;
							}
						}
					}
					existeEdital = true;
					break;
				}
			}
			if (existeEdital == false) {
				throw new EditalNaoEncontradoException();
			}
		}else if (existeAluno && disciplinasAluno.isEmpty()){
			return new ArrayList<Disciplina>();
		}else if (existeAluno == false){
			throw new AlunoNaoEncontradoException();
		}
		return  disciplinasAluno;
	}
	public boolean verificarMatricula(String matricula) throws AlunoJaMatriculadoException {
		for (Aluno aluno: todosOsAlunos) {
			if (aluno.getMatricula().equals(matricula)){
				throw new AlunoJaMatriculadoException();
			}

		}return false;
	}
	public boolean adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		if (!todosOsAlunos.isEmpty()) {
			verificarMatricula(a.getMatricula());
		    emailExiste(a.getEmail()); 
		}
		todosOsAlunos.add(a);
		return true;
	}

	public Aluno recuperarAlunoPorMatricula(String numMatricula) throws AlunoNaoEncontradoException {
		for (Aluno a: todosOsAlunos) {
			if (a.getMatricula().equals(numMatricula)) {
				return a;
			}
		}
		throw new AlunoNaoEncontradoException();
	}


	public ArrayList<Aluno> getTodosOsAlunos() {
		return todosOsAlunos;
	}

	public void setTodosOsAlunos(ArrayList<Aluno> alunos) {
		todosOsAlunos = alunos;
	}

	public ArrayList<EditalDeMonitoria> getTodosOsEditais() {
		if (todosOsEditais == null) {
			return new ArrayList<EditalDeMonitoria>();
		} else {
			return todosOsEditais;
		}
	}

	public void setTodosOsEditais(ArrayList<EditalDeMonitoria> todosOsEditais) {
		this.todosOsEditais = todosOsEditais;
	}
	public Coordenador getCoordenador() {
		return coordenador;
	}
}
