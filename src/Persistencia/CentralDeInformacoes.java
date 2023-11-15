package Persistencia;

import java.util.ArrayList;

import Classes.Aluno;
import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Excecoes.AlunoJaMatriculadoException;
import Excecoes.AlunoNaoEncontradoException;
import Excecoes.EditalInvalidoException;
import Excecoes.EditalNaoEncontradoException;

public class CentralDeInformacoes {
	private ArrayList<Aluno> todosOsAlunos = new ArrayList<Aluno>();
	private ArrayList<EditalDeMonitoria> todosOsEditais = new ArrayList<EditalDeMonitoria>();
	
	
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
	
	public boolean adicionarAluno(Aluno a) throws AlunoJaMatriculadoException{
		if (!todosOsAlunos.isEmpty()) {
			for (Aluno aluno: todosOsAlunos) {
				if (aluno.getMatricula().equals(a.getMatricula())){
					throw new AlunoJaMatriculadoException();
				}
			}
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
}
