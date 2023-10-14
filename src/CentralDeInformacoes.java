import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList<Aluno> todosOsAlunos = new ArrayList<Aluno>();
	private ArrayList<EditalDeMonitoria> todosOsEditais = new ArrayList<EditalDeMonitoria>();
	
	
	public boolean adicionarEdital(EditalDeMonitoria edital){
		if (!(todosOsEditais.size() == 0)){
			for (EditalDeMonitoria editais: todosOsEditais) {
					if (editais.getId() == edital.getId()) {
						return false;
					}
			}
		}
		todosOsEditais.add(edital);
		return true;
	}

	public EditalDeMonitoria recuperarEditalPeloId(long id) {
		for (EditalDeMonitoria edital: todosOsEditais) {
			if (edital.getId() == id) {
				return edital;
			}
		}
		return null;
	}
	
	public ArrayList<Disciplina> recuperarInscriçõesDeUmAlunoEmUmEdital(String matricula, long id){
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
				return null;
			}
		}else if (existeAluno && disciplinasAluno.isEmpty()){
				return new ArrayList<Disciplina>();
		}else if (!existeAluno){
			return null;
		}
		return  disciplinasAluno;
	}
	
	public boolean adicionarAluno(Aluno a) {
		if (!todosOsAlunos.isEmpty()) {
			for (Aluno aluno: todosOsAlunos) {
				if (aluno.getMatricula().equals(a.getMatricula())){
					return false;
				}
			}
		}
		todosOsAlunos.add(a);
		return true;
	}
	
	public Aluno recuperarAlunoPorMatricula(String numMatricula) {
		for (Aluno a: todosOsAlunos) {
			if (a.getMatricula().equals(numMatricula)) {
				return a;
			}
		}
		return null;
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
